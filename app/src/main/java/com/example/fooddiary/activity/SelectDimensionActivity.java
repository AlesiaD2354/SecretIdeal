package com.example.fooddiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.example.fooddiary.databinding.ActivitySelectDimensionBinding;

public class SelectDimensionActivity extends AppCompatActivity {

    private ActivitySelectDimensionBinding selectDimensionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectDimensionBinding = ActivitySelectDimensionBinding.inflate(getLayoutInflater());
        setContentView(selectDimensionBinding.getRoot());

        String[] userDimensions = getResources().getStringArray(R.array.user_weight_dimensions);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dimensions_list_item, userDimensions);
        selectDimensionBinding.dimensions.setAdapter(adapter);

        selectDimensionBinding.dimensions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String userDimensions = textView.getText().toString();

                getIntent().putExtra("userDimensions", userDimensions);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });

        selectDimensionBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}