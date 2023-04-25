package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivitySelectHeightBinding;

public class SelectHeightActivity extends AppCompatActivity {

    private ActivitySelectHeightBinding selectHeightBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectHeightBinding = ActivitySelectHeightBinding.inflate(getLayoutInflater());
        setContentView(selectHeightBinding.getRoot());

        selectHeightBinding.numSelectHeight.setMaxValue(251);
        selectHeightBinding.numSelectHeight.setMinValue(60);
        selectHeightBinding.numSelectHeight.setValue(155);

        selectHeightBinding.txtSelectHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("userHeight", String.valueOf(selectHeightBinding.numSelectHeight.getValue()));
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        selectHeightBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}