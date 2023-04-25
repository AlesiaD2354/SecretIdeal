package com.example.fooddiary.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "USERS";

    public static final String _ID = "id";
    public static final String EMAIL = "email";
    public static final String FULLNAME = "fullname";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String ACTIVITY = "activity";
    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";
    public static final String GOAL = "goal";
    public static final String PHONE = "phone";

    public static final String WEIGHT_DATE = "weight_date";

    public static final String WEIGHT_YEAR = "weight_year";

    public static final String FOOD_TABLE = "FOOD";

    public static final String FOOD_ID = "id";
    public static final String FOOD_NAME = "food_name";
    public static final String FOOD_CALORIES = "food_calories";
    public static final String FOOD_FATS = "food_fats";
    public static final String FOOD_CARBOHYDRATES = "food_carbohydrates";
    public static final String FOOD_PROTEIN = "food_protein";
    public static final String FOOD_PORTION = "food_portion";
    public static final String FOOD_RDA = "food_rda";

    static final String DB_NAME = "SECRET_IDEAL.DB";

    static final int DB_VERSION = 6;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT, " + FULLNAME + " TEXT NOT NULL, "
            + PHONE + " TEXT, " + PASSWORD + " TEXT NOT NULL, " + AGE + " TEXT, " + GENDER + " TEXT, "
            + ACTIVITY + " TEXT, " + WEIGHT + " TEXT, " + HEIGHT + " TEXT, " + WEIGHT_DATE + " TEXT, " + WEIGHT_YEAR + " TEXT, " + GOAL + " TEXT);";

    public static final String CREATE_FOOD_TABLE = "create table " + FOOD_TABLE + "(" + FOOD_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FOOD_NAME + " TEXT, " + FOOD_CALORIES + " TEXT, "
            + FOOD_FATS + " TEXT, " + FOOD_CARBOHYDRATES + " TEXT, " + FOOD_PROTEIN + " TEXT, " + FOOD_PORTION + " TEXT, " + FOOD_RDA + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Белый Рис', '129', '0,28г', '27,9г', '2,66г', '100г', '6%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Лапша', '137', '2,06г', '25,01г', '4,51г', '100г', '7%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Спагетти', '157', '0,92г', '30,68г', '5,76г', '100г', '8%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Макароны или Лапша с Сыром', '203', '9,4г', '21,46г', '7,94г', '100г', '10%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Жареная Баранина', '266', '18,15г', '0г', '23,93г', '100г', '13%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Бекон', '541', '41,78г', '1,43г', '37,04г', '100г', '27%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Бекон (Жареный)', '533', '40,3г', '1,5г', '38,34г', '100г', '27%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Говяжий Фарш', '276', '18,58г', '0г', '25,35г', '100г', '14%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Говядина', '288', '19,54г', '0г', '26,33г', '100г', '14%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Жареная Курица', '237', '13,49г', '0г', '27,07г', '100г', '12%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Ванильный Йогурт (Обезжиренный)', '85', '1,25г', '13,8г', '4,93г', '100г', '4%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Молоко', '50', '2г', '4,71г', '3,29г', '100г', '2%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Молоко (с Низким Содержанием Жира)', '42', '0,97г', '4,99г', '3,37г', '100г', '2%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Творог с Фруктами', '97', '3,85г', '4,61г', '10,69г', '100г', '5%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Вода', '0', '0г', '0г', '0г', '100г', '0%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Кофе Латте', '33', '1,35г', '3,03г', '2,16г', '100г', '2%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Кофе Эспрессо', '2', '0,18г', '0г', '0,12г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Растворимый Кофе (из Порошка)', '2', '0г', '0,38г', '0,11г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Чай', '1', '0г', '0,3г', '0г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Чай Несладкий', '1', '0г', '0,3г', '0,3г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Травяной Чай', '1', '0г', '0,2г', '0г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Зеленый Чай', '1', '0г', '0,2г', '0г', '100г', '1%')");
        db.execSQL("insert into " + FOOD_TABLE + "(" + FOOD_NAME + ","
                + FOOD_CALORIES + "," + FOOD_FATS + "," + FOOD_CARBOHYDRATES + ","
                + FOOD_PROTEIN + "," + FOOD_PORTION + "," + FOOD_RDA + ") values('Чай с Сахаром', '20', '0г', '5,28г', '0,01г', '100г', '1%')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE);
        onCreate(db);
    }
}
