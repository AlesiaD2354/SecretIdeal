package com.example.fooddiary.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fooddiary.R;
import com.example.fooddiary.databinding.ActivityProfileBinding;
import com.example.fooddiary.util.DBManager;
import com.google.android.material.navigation.NavigationBarView;

// TODO: Это активити переделать в фрагмент

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding profileBinding;

    private final int GALLERY_REQ_CODE = 1000;

    DBManager dbManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            profileBinding.userImg.setImageURI(data.getData());
        } else {
            Toast.makeText(this, "Вы не выбрали изображение", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(profileBinding.getRoot());

        Intent intent = getIntent();
        String username = intent.getStringExtra("loginStr");
        String usernameFromReg = intent.getStringExtra("registerStr");

        profileBinding.txtUsername.setText(username);



        profileBinding.btnUserWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weightIntent = new Intent(ProfileActivity.this, UserWeightActivity.class);
                weightIntent.putExtra("loginStr", username);
                weightIntent.putExtra("registerStr", usernameFromReg);
                startActivity(weightIntent);
            }
        });

        profileBinding.userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, GALLERY_REQ_CODE);
            }
        });

        profileBinding.bottomNav.setSelectedItemId(R.id.item1);

        profileBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        return true;
                    case R.id.item2:
                        Intent diaryIntent = new Intent(ProfileActivity.this, DiaryActivity.class);
                        diaryIntent.putExtra("username", username);
                        diaryIntent.putExtra("userFromReg", usernameFromReg);
                        startActivityForResult(diaryIntent, 200);
                        return true;
                    case R.id.item3:
                        Intent settingsIntent = new Intent(ProfileActivity.this, SettingsActivity.class);
                        settingsIntent.putExtra("username", username);
                        settingsIntent.putExtra("userFromReg", usernameFromReg);
                        startActivity(settingsIntent);
                        return true;
                }
                return false;
            }
        });
    }

}