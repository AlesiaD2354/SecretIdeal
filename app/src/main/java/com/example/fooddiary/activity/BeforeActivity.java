package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivityBeforeBinding;

public class BeforeActivity extends AppCompatActivity {

    private ActivityBeforeBinding beforeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeBinding = ActivityBeforeBinding.inflate(getLayoutInflater());
        setContentView(beforeBinding.getRoot());

        beforeBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        beforeBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}