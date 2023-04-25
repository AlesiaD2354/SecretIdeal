package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooddiary.databinding.ActivityRegisterBinding;
import com.example.fooddiary.util.DBManager;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        dbManager = new DBManager(this);
        dbManager.open();

        registerBinding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registerBinding.email.getText().toString();
                String fullname = registerBinding.fullName.getText().toString();
                String password = registerBinding.password.getText().toString();
                String phone = registerBinding.phone.getText().toString();

                if(email.equals("") || fullname.equals("") || password.equals("") || phone.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.insert(email, fullname, password, phone);
                    Toast.makeText(RegisterActivity.this, "Регистрация выполнена успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}