package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fooddiary.R;
import com.example.fooddiary.adapter.AddFoodAdapter;
import com.example.fooddiary.databinding.ActivityAddFoodBinding;
import com.example.fooddiary.interfaces.FoodListener;
import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.util.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AddFoodActivity extends AppCompatActivity implements AddFoodAdapter.OnItemChecked {

    private ActivityAddFoodBinding addFoodBinding;
    ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

    ArrayList<String> selectedItems = new ArrayList<>();

    HashMap<String, ArrayList<String>> foodByTime = new HashMap<>();

    private DBManager dbManager;
    AddFoodAdapter adapter;

    String[] spinnerData = {"Завтрак", "Обед", "Ужин", "Перекус/Другое"};

    Calendar dateAndTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFoodBinding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(addFoodBinding.getRoot());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData);

        addFoodBinding.spinner.setAdapter(arrayAdapter);

//        addFoodBinding.spinner.setSelection(0);

        dbManager = new DBManager(this);
        dbManager.open();
        displayFoodData();

        init();

        setInitialDateTime();

        addFoodBinding.txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(adapter.selectList());
                Intent backIntent = new Intent();
                backIntent.putExtra("foodName", adapter.selectList());
                backIntent.putExtra("foodByTime", foodByTime);
                setResult(RESULT_OK, backIntent);
                finish();
            }
        });

        addFoodBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodByTime.put(addFoodBinding.spinner.getSelectedItem().toString(), adapter.selectList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addFoodBinding.txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addFoodBinding.txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<FoodItem> filteredList = new ArrayList<>();

        for (FoodItem foodItem: foodItems) {
            if (foodItem.getFoodName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodItem);
            }
        }
        adapter.filterList(filteredList);
    }

    private void displayFoodData() {
        Cursor cursor = dbManager.getFoodData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                foodItems.add(new FoodItem(cursor.getString(1), cursor.getString(6), cursor.getString(7), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }
        }
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AddFoodAdapter(this, foodItems);

        addFoodBinding.rcList.setHasFixedSize(true);

        addFoodBinding.rcList.setAdapter(adapter);
        addFoodBinding.rcList.setLayoutManager(manager);
    }

    private void setInitialDateTime() {
        addFoodBinding.txtCurrentDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY));
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    @Override
    public void onItemChecked(Intent intent) {
        selectedItems.add(intent.getStringExtra("foodName"));
        addFoodBinding.txtSubmit.setVisibility(View.VISIBLE);
        addFoodBinding.txtCancel.setVisibility(View.GONE);
    }
}