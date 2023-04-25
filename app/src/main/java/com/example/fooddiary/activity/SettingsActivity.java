package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding settingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsBinding.getRoot());

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        System.out.println(username);
        settingsBinding.btnManageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(SettingsActivity.this, ManageAccountsActivity.class);
                settingsIntent.putExtra("username", username);
                startActivity(settingsIntent);
            }
        });

        settingsBinding.btnRDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rdaIntent = new Intent(SettingsActivity.this, RDAActivity.class);
                rdaIntent.putExtra("username", username);
                startActivity(rdaIntent);
            }
        });

        settingsBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}