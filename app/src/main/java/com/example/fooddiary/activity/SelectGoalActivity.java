package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.example.fooddiary.databinding.ActivitySelectGoalBinding;

public class SelectGoalActivity extends AppCompatActivity {

    private ActivitySelectGoalBinding selectGoalBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectGoalBinding = ActivitySelectGoalBinding.inflate(getLayoutInflater());
        setContentView(selectGoalBinding.getRoot());

        String[] userGoal = getResources().getStringArray(R.array.user_goal);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.goals_list_item, userGoal);
        selectGoalBinding.goals.setAdapter(adapter);

        selectGoalBinding.goals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String userGoal = textView.getText().toString();

                getIntent().putExtra("userGoal", userGoal);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
        selectGoalBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}