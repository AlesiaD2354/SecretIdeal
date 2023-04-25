package com.example.fooddiary.interfaces;

import com.example.fooddiary.model.FoodItem;

import java.util.ArrayList;

public interface FoodListener {

    void onFoodChange(ArrayList<FoodItem> changedList);
}