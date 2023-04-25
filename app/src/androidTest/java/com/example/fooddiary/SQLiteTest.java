package com.example.fooddiary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import android.database.Cursor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.fooddiary.util.DBManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SQLiteTest {
    private DBManager dbManager;

    @Before
    public void setUp() {
        dbManager = new DBManager(InstrumentationRegistry.getInstrumentation().getTargetContext());
        dbManager.open();
    }

    @After
    public void finish() {
        dbManager.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(dbManager);
    }

    @Test
    public void testShouldAddExpenseType() throws Exception {
        dbManager.insert("user@gmail.com", "user", "12345", "89005551122");
        String userByEmail = dbManager.findUsernameByEmail("user@gmail.com");
        assertThat(userByEmail, is("user"));
    }

    @Test
    public void deleteOne() {
        dbManager.delete(1);
        Cursor cursor = dbManager.findById("1");

        assertThat(cursor.moveToNext(), is(false));
    }

    @Test
    public void deleteAll() {
        dbManager.deleteAll();

        Cursor cursor = dbManager.fetch();

        assertThat(cursor.moveToFirst(), is(false));
    }
}
