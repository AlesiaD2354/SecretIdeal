package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.fooddiary.databinding.ActivityAddUserWeightBinding;
import com.example.fooddiary.util.DBManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddUserWeightActivity extends AppCompatActivity {

    private ActivityAddUserWeightBinding addUserWeightBinding;

    DBManager dbManager;

    Calendar dateAndTime = Calendar.getInstance();

    double editedWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addUserWeightBinding = ActivityAddUserWeightBinding.inflate(getLayoutInflater());
        setContentView(addUserWeightBinding.getRoot());

        setInitialDateTime();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        dbManager = new DBManager(this);
        dbManager.open();

        addUserWeightBinding.txtEditedWeight.setText(intent.getStringExtra("weight"));

        addUserWeightBinding.imgCancelWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addUserWeightBinding.currentDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        addUserWeightBinding.btnAddWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double defaultWeight = Double.parseDouble(addUserWeightBinding.txtEditedWeight.getText().toString().replace(",","."));
                editedWeight = defaultWeight + 0.1;
                System.out.println(defaultWeight);
                addUserWeightBinding.txtEditedWeight.setText(String.valueOf(String.format("%.1f",editedWeight)));
                setResult(RESULT_OK, getIntent());
            }
        });

        addUserWeightBinding.btnRemoveWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weight = Double.parseDouble(addUserWeightBinding.txtEditedWeight.getText().toString().replace(",","."));
                editedWeight = weight - 0.1;
                addUserWeightBinding.txtEditedWeight.setText(String.valueOf(String.format("%.1f",editedWeight)));
            }
        });

        addUserWeightBinding.imgSubmitWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dateYear = new Intent();
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
                String dateText = dateFormat.format(currentDate);
                DateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                String yearText = yearFormat.format(currentDate);

                ArrayList<String> dateData = new ArrayList<>();
                getIntent().putExtra("editedWeight", String.format("%.1f", editedWeight));
                getIntent().putExtra("dateYear", dateData);
                dbManager.updateUserWeight(username, String.valueOf(editedWeight), dateText, yearText);
                setResult(RESULT_OK, getIntent().putExtra("username", username));
                finish();
            }
        });
    }

    public void setDate(View v) {
        new DatePickerDialog(AddUserWeightActivity.this, d,
                            dateAndTime.get(Calendar.YEAR),
                            dateAndTime.get(Calendar.MONTH),
                            dateAndTime.get(Calendar.DAY_OF_MONTH))
                            .show();
    }

    public void setTime(View v) {
        new TimePickerDialog(AddUserWeightActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialDateTime() {
        addUserWeightBinding.currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                            | DateUtils.FORMAT_SHOW_TIME));
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
}