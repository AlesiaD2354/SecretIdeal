package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.example.fooddiary.databinding.ActivitySelectBinding;

public class SelectActivity extends AppCompatActivity {

    private ActivitySelectBinding selectActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectActivityBinding = ActivitySelectBinding.inflate(getLayoutInflater());
        setContentView(selectActivityBinding.getRoot());

        String[] userActivities = getResources().getStringArray(R.array.user_activity);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activities_list_item, userActivities);
        selectActivityBinding.activities.setAdapter(adapter);

        selectActivityBinding.activities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String userActivity = textView.getText().toString();

                getIntent().putExtra("userActivity", userActivity);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        selectActivityBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}