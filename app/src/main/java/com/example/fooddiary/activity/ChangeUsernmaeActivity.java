package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooddiary.databinding.ActivityChangeUsernmaeBinding;
import com.example.fooddiary.util.DBManager;

public class ChangeUsernmaeActivity extends AppCompatActivity {

    private ActivityChangeUsernmaeBinding changeUsernmaeBinding;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeUsernmaeBinding = ActivityChangeUsernmaeBinding.inflate(getLayoutInflater());
        setContentView(changeUsernmaeBinding.getRoot());

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        dbManager = new DBManager(this);
        dbManager.open();

        changeUsernmaeBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changeUsernmaeBinding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updatedUsername = new Intent();
                dbManager.updateUsername(username, changeUsernmaeBinding.editUsername.getText().toString());
                Toast.makeText(ChangeUsernmaeActivity.this, "Имя пользователя успешно изменено", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, updatedUsername.putExtra("newUsername", changeUsernmaeBinding.editUsername.getText().toString()));
                finish();
            }
        });

        changeUsernmaeBinding.txtChangeUsername.setText(username);
    }
}