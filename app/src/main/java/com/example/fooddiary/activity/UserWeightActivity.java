package com.example.fooddiary.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooddiary.adapter.UserWeightAdapter;
import com.example.fooddiary.databinding.ActivityUserWeightBinding;
import com.example.fooddiary.util.DBManager;

import java.util.ArrayList;

// TODO: Сделать фрагментом
public class UserWeightActivity extends AppCompatActivity {

    private ActivityUserWeightBinding userWeightBinding;

    UserWeightAdapter adapter;

    ArrayList<String> year = new ArrayList<String>();

    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<String> weight = new ArrayList<String>();

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userWeightBinding = ActivityUserWeightBinding.inflate(getLayoutInflater());
        setContentView(userWeightBinding.getRoot());

        Intent intent = getIntent();
        String username = intent.getStringExtra("loginStr");

        dbManager = new DBManager(this);
        dbManager.open();

        initRecyclerView(username);

        if(dbManager.findByWeight(username) != null) {
            userWeightBinding.txtWeight.setText(dbManager.findByWeight(username));
        }

        userWeightBinding.imgAddUserWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addUserWeightIntent = new Intent(UserWeightActivity.this, AddUserWeightActivity.class);
                addUserWeightIntent.putExtra("username", username);
                addUserWeightIntent.putExtra("weight", userWeightBinding.txtWeight.getText().toString());
                startActivityForResult(addUserWeightIntent, 100);
            }
        });

        userWeightBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayWeight(String username) {
        Cursor cursor = dbManager.findYearByUsername(username);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                year.add(cursor.getString(0));
                dates.add(cursor.getString(1));
                weight.add(cursor.getString(2));
            }
        }
    }

    public void initRecyclerView(String username) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        displayWeight(username);
        for (String yeas : year) {
            if (years.contains(yeas)) {
                years.add("");
            } else {
                years.add(yeas);
            }
        }

        adapter = new UserWeightAdapter(this, years, dates, weight);

        userWeightBinding.rcWeightByDate.setHasFixedSize(true);

        userWeightBinding.rcWeightByDate.setAdapter(adapter);
        userWeightBinding.rcWeightByDate.setLayoutManager(manager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> dateYear = data.getStringArrayListExtra("dateYear");

        if(requestCode == 100 && resultCode == RESULT_OK && data != null) {
            userWeightBinding.txtWeight.setText(data.getStringExtra("editedWeight"));
            refreshWeight(userWeightBinding.txtWeight.getText().toString());
            System.out.println(dateYear);
//            refreshYear(dateYear.get(0));
//            refreshDate(dateYear.get(1));
        }
    }

    private void refreshWeight(String updatedWeight) {
        if(!updatedWeight.isEmpty()) {
            weight.set(0, updatedWeight);
        }
        adapter.notifyDataSetChanged();
    }
    private void refreshYear(String year) {
        if(!year.isEmpty()) {
            years.set(0, year);
        }
        adapter.notifyDataSetChanged();
    }
    private void refreshDate(String date) {
        if(!date.isEmpty()) {
            dates.set(0, date);
        }
        adapter.notifyDataSetChanged();
    }
}