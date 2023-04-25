package com.example.fooddiary.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String email, String fullname, String password, String phone) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.FULLNAME, fullname);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        contentValue.put(DatabaseHelper.PHONE, phone);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public void insertFood(String food_name, String food_calories, String food_fats, String food_carbohydrates, String food_protein, String food_portion, String food_rda) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.FOOD_NAME, food_name);
        contentValue.put(DatabaseHelper.FOOD_CALORIES, food_calories);
        contentValue.put(DatabaseHelper.FOOD_FATS, food_fats);
        contentValue.put(DatabaseHelper.FOOD_CARBOHYDRATES, food_carbohydrates);
        contentValue.put(DatabaseHelper.FOOD_PROTEIN, food_protein);
        contentValue.put(DatabaseHelper.FOOD_PORTION, food_portion);
        contentValue.put(DatabaseHelper.FOOD_RDA, food_rda);
        database.insert(DatabaseHelper.FOOD_TABLE, null, contentValue);
    }

    public void insertWeightDate(String weightDate, String weightYear) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.WEIGHT_DATE, weightDate);
        contentValue.put(DatabaseHelper.WEIGHT_YEAR, weightYear);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {DatabaseHelper._ID, DatabaseHelper.EMAIL, DatabaseHelper.FULLNAME, DatabaseHelper.PASSWORD, DatabaseHelper.PHONE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getFoodData() {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from FOOD", null);
        return cursor;
    }

    public Cursor findNutrionDataByFoodName(String foodName) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select food_fats, food_carbohydrates, food_protein, food_rda, food_calories, food_portion from FOOD where food_name=?", new String[] {foodName});
        return cursor;
    }

    public int update(long _id, String email, String fullname, String password, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.FULLNAME, fullname);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        contentValues.put(DatabaseHelper.PHONE, phone);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public void deleteByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.FULLNAME + "=?", new String[] {username});
    }

    public void deleteAll() {
        database.delete(DatabaseHelper.TABLE_NAME, null, null);
    }

    public Boolean checkUser(String email) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from USERS where email = ?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsersPassword(String email, String password) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from USERS where email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String findUsernameByEmail(String email) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select fullname from USERS where email = ?", new String[] {email});
        String user = "User not found";
        if(cursor.moveToFirst()) user = cursor.getString(0);
        cursor.close();
        database.close();
        return user;
    }

    public Cursor findById(String id) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from USERS where id = ?", new String[] {id});
        return cursor;
    }

    public String findEmailByUser(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select email from USERS where fullname = ?", new String[] {username});
        String email = "User email not found";
        if(cursor.moveToFirst()) email = cursor.getString(0);
        cursor.close();
        database.close();
        return email;
    }

    public boolean updateUserRda(String username, String age, String gender, String activity, String weight,
                              String height, String goal) {
        database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.AGE, age);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.ACTIVITY, activity);
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.HEIGHT, height);
        contentValues.put(DatabaseHelper.GOAL, goal);
        long ins = database.update(DatabaseHelper.TABLE_NAME, contentValues, "fullname = ?", new String[] {username});
        return ins != -1;
    }


    public String findHeightByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select height from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String findAgeByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select age from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String findUserActivityByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select activity from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String findGoalByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select goal from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String findGenderByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select gender from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String findByWeight(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select weight from USERS where fullname = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Cursor getWeightByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select weight from USERS where fullname = ?", new String[] {username});
        return cursor;
    }

    public Cursor findYearByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select weight_year, weight_date, weight from USERS where fullname = ?", new String[] {username});
        return cursor;
    }

    public Cursor findDateByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select weight_date from USERS where fullname = ?", new String[] {username});
        return cursor;
    }

    public boolean updateUsername(String oldUsername, String username) {
        database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FULLNAME, oldUsername);
        long ins = database.update(DatabaseHelper.TABLE_NAME, contentValues, "fullname = ?", new String[] {username});
        return ins != -1;
    }

    public void deleteUser(String username) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.FULLNAME + " = " + username, null);
    }

    public boolean updateUserWeight(String username, String weight, String weightDate, String weightYear) {
        database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.WEIGHT_DATE, weightDate);
        contentValues.put(DatabaseHelper.WEIGHT_YEAR, weightYear);
//        contentValues.put(DatabaseHelper.WEIGHT_DATE, weight_date);
        long ins = database.update(DatabaseHelper.TABLE_NAME, contentValues, "fullname = ?", new String[] {username});
        return ins != -1;
    }
}
