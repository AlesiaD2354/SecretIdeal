package com.example.fooddiary.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fooddiary.R;
import com.example.fooddiary.adapter.DiaryAdapter;
import com.example.fooddiary.databinding.ActivityDiaryBinding;
import com.example.fooddiary.model.ButtonItem;
import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.util.DBManager;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryActivity extends AppCompatActivity {
    private ActivityDiaryBinding diaryBinding;
    ArrayList<ButtonItem> buttonItems = new ArrayList<>();
    DiaryAdapter adapter;

    DBManager dbManager;

    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryBinding = ActivityDiaryBinding.inflate(getLayoutInflater());
        setContentView(diaryBinding.getRoot());

        diaryBinding.bottomNavigationView.setSelectedItemId(R.id.item2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        dbManager = new DBManager(this);
        dbManager.open();
        initButtons();
        setInitialDateTime();
        initRecyclerView();

        diaryBinding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        setResult(RESULT_OK, getIntent());
                        finish();
                        return true;
                    case R.id.item2:
                        return true;
                    case R.id.item3:
                        Intent settingsIntent = new Intent(DiaryActivity.this, SettingsActivity.class);
                        settingsIntent.putExtra("username", username);
                        startActivity(settingsIntent);
                        return true;
                }
                return false;
            }
        });

        diaryBinding.curDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        diaryBinding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(DiaryActivity.this, AddFoodActivity.class);
                startActivityForResult(searchIntent, 100);
            }
        });
    }

    public void initButtons() {
        ButtonItem breakfast = new ButtonItem("Завтрак", R.drawable.sunrise, R.drawable.baseline_add);
        ButtonItem lunch = new ButtonItem("Обед", R.drawable.sun, R.drawable.baseline_add);
        ButtonItem dinner = new ButtonItem("Ужин", R.drawable.sunset, R.drawable.baseline_add);
        ButtonItem snack = new ButtonItem("Перекус/Другое", R.drawable.night, R.drawable.baseline_add);

        buttonItems.add(breakfast);
        buttonItems.add(lunch);
        buttonItems.add(dinner);
        buttonItems.add(snack);
    }

    public void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new DiaryAdapter(this, buttonItems);

        diaryBinding.rcBtnList.setHasFixedSize(true);
        diaryBinding.rcBtnList.setAdapter(adapter);
        diaryBinding.rcBtnList.setLayoutManager(manager);
    }

    public void setDate(View v) {
        new DatePickerDialog(DiaryActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTime(View v) {
        new TimePickerDialog(DiaryActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialDateTime() {
        diaryBinding.curDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        HashMap<String, ArrayList<String>> hashMap = (HashMap<String, ArrayList<String>>)data.getSerializableExtra("foodByTime");

        ArrayList<String> breakFastFood = hashMap.get("Завтрак");
        ArrayList<String> lunchFood = hashMap.get("Обед");
        ArrayList<String> dinnerFood = hashMap.get("Ужин");
        ArrayList<String> snackFood = hashMap.get("Перекус/Другое");

        Cursor breakFastCursor;
        Cursor lunchCursor;
        Cursor dinnerCursor;
        Cursor snackCursor;

        System.out.println(hashMap);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            for(Map.Entry entry : hashMap.entrySet()) {
                if (entry.getKey().equals("Завтрак") && breakFastFood != null && breakFastFood.size() != 0) {
                    breakFastCursor = dbManager.findNutrionDataByFoodName(breakFastFood.get(0));
                    if (breakFastCursor.getCount() == 0) {
                        Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        while (breakFastCursor.moveToNext()) {
                            buttonItems.get(0).setFoodTitle(breakFastFood.get(0));
                            buttonItems.get(0).setFoodFats(breakFastCursor.getString(0));
                            buttonItems.get(0).setFoodCarbohydrates(breakFastCursor.getString(1));
                            buttonItems.get(0).setFoodProtein(breakFastCursor.getString(2));
                            buttonItems.get(0).setFoodRda(breakFastCursor.getString(3));
                            buttonItems.get(0).setFoodCalories(breakFastCursor.getString(4));
                            buttonItems.get(0).setFoodPortion(breakFastCursor.getString(5));

                            diaryBinding.txtDiaryFoodFats.setText(breakFastCursor.getString(0));
                            diaryBinding.txtDiaryFoodCarbohydrates.setText(breakFastCursor.getString(1));
                            diaryBinding.txtDiaryFoodProtein.setText(breakFastCursor.getString(2));
                            diaryBinding.txtDiaryFoodRDA.setText(breakFastCursor.getString(3));
                            diaryBinding.txtDiaryFoodCalories.setText(breakFastCursor.getString(4));
                        }
                    }
                } else if(entry.getKey().equals("Обед") && lunchFood != null && lunchFood.size() != 0) {
                    lunchCursor = dbManager.findNutrionDataByFoodName(lunchFood.get(0));
                    if (lunchCursor.getCount() == 0) {
                        Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        while (lunchCursor.moveToNext()) {
                            buttonItems.get(1).setFoodTitle(lunchFood.get(0));
                            buttonItems.get(1).setFoodFats(lunchCursor.getString(0));
                            buttonItems.get(1).setFoodCarbohydrates(lunchCursor.getString(1));
                            buttonItems.get(1).setFoodProtein(lunchCursor.getString(2));
                            buttonItems.get(1).setFoodRda(lunchCursor.getString(3));
                            buttonItems.get(1).setFoodCalories(lunchCursor.getString(4));
                            buttonItems.get(1).setFoodPortion(lunchCursor.getString(5));

                            diaryBinding.txtDiaryFoodFats.setText(lunchCursor.getString(0));
                            diaryBinding.txtDiaryFoodCarbohydrates.setText(lunchCursor.getString(1));
                            diaryBinding.txtDiaryFoodProtein.setText(lunchCursor.getString(2));
                            diaryBinding.txtDiaryFoodRDA.setText(lunchCursor.getString(3));
                            diaryBinding.txtDiaryFoodCalories.setText(lunchCursor.getString(4));
                        }
                    }

                } else if (entry.getKey().equals("Ужин") && dinnerFood != null && dinnerFood.size() != 0) {
                    dinnerCursor = dbManager.findNutrionDataByFoodName(dinnerFood.get(0));
                    if (dinnerCursor.getCount() == 0) {
                        Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        while (dinnerCursor.moveToNext()) {
                            buttonItems.get(2).setFoodTitle(dinnerFood.get(0));
                            buttonItems.get(2).setFoodFats(dinnerCursor.getString(0));
                            buttonItems.get(2).setFoodCarbohydrates(dinnerCursor.getString(1));
                            buttonItems.get(2).setFoodProtein(dinnerCursor.getString(2));
                            buttonItems.get(2).setFoodRda(dinnerCursor.getString(3));
                            buttonItems.get(2).setFoodCalories(dinnerCursor.getString(4));
                            buttonItems.get(2).setFoodPortion(dinnerCursor.getString(5));

                            diaryBinding.txtDiaryFoodFats.setText(dinnerCursor.getString(0));
                            diaryBinding.txtDiaryFoodCarbohydrates.setText(dinnerCursor.getString(1));
                            diaryBinding.txtDiaryFoodProtein.setText(dinnerCursor.getString(2));
                            diaryBinding.txtDiaryFoodRDA.setText(dinnerCursor.getString(3));
                            diaryBinding.txtDiaryFoodCalories.setText(dinnerCursor.getString(4));
                        }
                    }
                } else if(entry.getKey().equals("Перекус/Другое") && snackFood != null && snackFood.size() != 0){
                    snackCursor = dbManager.findNutrionDataByFoodName(snackFood.get(0));
                    if (snackCursor.getCount() == 0) {
                        Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        while (snackCursor.moveToNext()) {
                            buttonItems.get(3).setFoodTitle(snackFood.get(0));
                            buttonItems.get(3).setFoodFats(snackCursor.getString(0));
                            buttonItems.get(3).setFoodCarbohydrates(snackCursor.getString(1));
                            buttonItems.get(3).setFoodProtein(snackCursor.getString(2));
                            buttonItems.get(3).setFoodRda(snackCursor.getString(3));
                            buttonItems.get(3).setFoodCalories(snackCursor.getString(4));
                            buttonItems.get(3).setFoodPortion(snackCursor.getString(5));

                            diaryBinding.txtDiaryFoodFats.setText(snackCursor.getString(0));
                            diaryBinding.txtDiaryFoodCarbohydrates.setText(snackCursor.getString(1));
                            diaryBinding.txtDiaryFoodProtein.setText(snackCursor.getString(2));
                            diaryBinding.txtDiaryFoodRDA.setText(snackCursor.getString(3));
                            diaryBinding.txtDiaryFoodCalories.setText(snackCursor.getString(4));
                        }
                    }
                } else {
                    Toast.makeText(this, "Вы не выбрали время приема пищи", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

}