package com.example.fooddiary.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooddiary.databinding.ActivityLoginBinding;
import com.example.fooddiary.util.DBManager;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    DBManager dbManager;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        dbManager = new DBManager(this);
        dbManager.open();

        loginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginBinding.email.getText().toString();
                String password = loginBinding.password.getText().toString();
                if(email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Поля не должны быть пустыми", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = dbManager.checkUsersPassword(email, password);
                    if(checkUserPass == true) {
                        Toast.makeText(LoginActivity.this, "Авторизация выполнена успешно", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        String username = dbManager.findUsernameByEmail(email);
                        intent.putExtra("loginStr", username);
                        activityResultLauncher.launch(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}