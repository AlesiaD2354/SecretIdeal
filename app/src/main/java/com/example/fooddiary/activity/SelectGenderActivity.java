package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.example.fooddiary.databinding.ActivitySelectGenderBinding;

public class SelectGenderActivity extends AppCompatActivity {

    private ActivitySelectGenderBinding selectGenderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectGenderBinding = ActivitySelectGenderBinding.inflate(getLayoutInflater());
        setContentView(selectGenderBinding.getRoot());

        String[] userGender = getResources().getStringArray(R.array.user_gender);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.genders_list_item, userGender);
        selectGenderBinding.genders.setAdapter(adapter);

        selectGenderBinding.genders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String userGender = textView.getText().toString();

                getIntent().putExtra("userGender", userGender);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        selectGenderBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}