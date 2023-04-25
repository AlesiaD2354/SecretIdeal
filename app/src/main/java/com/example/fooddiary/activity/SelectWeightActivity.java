package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivitySelectWeightBinding;

public class SelectWeightActivity extends AppCompatActivity {

    private ActivitySelectWeightBinding selectWeightBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectWeightBinding = ActivitySelectWeightBinding.inflate(getLayoutInflater());
        setContentView(selectWeightBinding.getRoot());

        selectWeightBinding.weightPicker.setMaxValue(150);
        selectWeightBinding.weightPicker.setMinValue(40);
        selectWeightBinding.weightPicker.setValue(50);

        selectWeightBinding.subWeightPicker.setMaxValue(9);
        selectWeightBinding.subWeightPicker.setMinValue(0);
        selectWeightBinding.subWeightPicker.setValue(2);

        selectWeightBinding.weightPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selectWeightBinding.subWeightPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selectWeightBinding.txtSelectWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("userWeight", String.valueOf(selectWeightBinding.weightPicker.getValue()));
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        selectWeightBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}