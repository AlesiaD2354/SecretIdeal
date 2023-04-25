package com.example.fooddiary.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddiary.databinding.ActivityManageAccountsBinding;
import com.example.fooddiary.util.DBManager;

public class ManageAccountsActivity extends AppCompatActivity {

    private ActivityManageAccountsBinding manageAccountsBinding;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manageAccountsBinding = ActivityManageAccountsBinding.inflate(getLayoutInflater());
        setContentView(manageAccountsBinding.getRoot());

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        dbManager = new DBManager(this);
        dbManager.open();
        manageAccountsBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        manageAccountsBinding.btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeUsernameIntent = new Intent(ManageAccountsActivity.this, ChangeUsernmaeActivity.class);
                changeUsernameIntent.putExtra("username", username);
                startActivityForResult(changeUsernameIntent, 100);
            }
        });

        manageAccountsBinding.txtUserEmail.setText(dbManager.findEmailByUser(username));

        manageAccountsBinding.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteUserIntent = new Intent(ManageAccountsActivity.this, DeleteUserActivity.class);
                deleteUserIntent.putExtra("username", username);
                startActivity(deleteUserIntent);
            }
        });

        manageAccountsBinding.txtManageUsername.setText(username);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data != null) {
            manageAccountsBinding.txtManageUsername.setText(data.getStringExtra("newUsername"));
        }
    }
}