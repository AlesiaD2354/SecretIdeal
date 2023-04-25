package com.example.fooddiary.model;

import java.io.Serializable;

public class ButtonItem implements Serializable {
    private String title, foodTitle, foodFats, foodCarbohydrates, foodCalories, foodProtein, foodRda, foodPortion;
    private int imageLeft, imageRight;

    private boolean isExpandable;

    public ButtonItem(String title, int imageLeft, int imageRight) {
        this.title = title;
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.isExpandable = false;
    }

    public ButtonItem(String title, String foodTitle, int imageLeft, int imageRight) {
        this.title = title;
        this.foodTitle = foodTitle;
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.isExpandable = false;
    }

    public String getFoodFats() {
        return foodFats;
    }

    public String getFoodCarbohydrates() {
        return foodCarbohydrates;
    }

    public String getFoodPortion() {
        return foodPortion;
    }

    public void setFoodPortion(String foodPortion) {
        this.foodPortion = foodPortion;
    }

    public void setFoodCarbohydrates(String foodCarbohydrates) {
        this.foodCarbohydrates = foodCarbohydrates;
    }

    public void setFoodFats(String foodFats) {
        this.foodFats = foodFats;
    }

    public String getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(String foodCalories) {
        this.foodCalories = foodCalories;
    }

    public String getFoodProtein() {
        return foodProtein;
    }

    public void setFoodProtein(String foodProtein) {
        this.foodProtein = foodProtein;
    }

    public String getFoodRda() {
        return foodRda;
    }

    public void setFoodRda(String foodRda) {
        this.foodRda = foodRda;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public int getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(int imageLeft) {
        this.imageLeft = imageLeft;
    }

    public int getImageRight() {
        return imageRight;
    }

    public void setImageRight(int imageRight) {
        this.imageRight = imageRight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
