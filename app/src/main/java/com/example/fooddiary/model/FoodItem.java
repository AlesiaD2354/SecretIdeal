package com.example.fooddiary.model;

public class FoodItem {
    private String foodName;
    private String portion;
    private String rda;
    private String calories;
    private String foodFats;
    private String carbohydrates;
    private String protein;
    private boolean isExpandable, isSelected;

    public FoodItem(String foodName, String portion, String rda, String calories, String foodFats, String carbohydrates, String protein) {
        this.foodName = foodName;
        this.portion = portion;
        this.rda = rda;
        this.calories = calories;
        this.foodFats = foodFats;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.isExpandable = false;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getFoodFats() {
        return foodFats;
    }

    public void setFoodFats(String foodFats) {
        this.foodFats = foodFats;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getRda() {
        return rda;
    }

    public void setRda(String rda) {
        this.rda = rda;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

}
