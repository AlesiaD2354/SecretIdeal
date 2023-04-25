package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivityDeleteUserBinding;
import com.example.fooddiary.util.DBManager;

public class DeleteUserActivity extends AppCompatActivity {

    private ActivityDeleteUserBinding deleteUserBinding;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteUserBinding = ActivityDeleteUserBinding.inflate(getLayoutInflater());
        setContentView(deleteUserBinding.getRoot());

        dbManager = new DBManager(this);
        dbManager.open();
        System.out.println(getIntent().getStringExtra("username"));

        deleteUserBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteByUsername(getIntent().getStringExtra("username"));
                finishAffinity();
                System.exit(0);
            }
        });

        deleteUserBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}