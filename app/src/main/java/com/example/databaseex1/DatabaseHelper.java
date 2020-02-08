package com.example.databaseex1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "myDatabase";
    static int DATABASE_VERSION = 1;
    static String TABLE_NAME = "employees";
    static String COLUMN_ID = "id";
    static String COLUMN_NAME = "name";
    static String COLUMN_CITY = "city";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " VARCHAR(200) NOT NULL, " +
                COLUMN_CITY + " VARCHAR(200) NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    boolean addEmployee(Employee emp){
        SQLiteDatabase db = getWritableDatabase();

//        String sql = "INSERT INTO "+ TABLE_NAME +
//                " (name, city) VALUES(?, ?)";
//
//        db.rawQuery(sql, new String[]{name, city});

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, emp.getName());
        cv.put(COLUMN_CITY, emp.getCity());

        long rows = db.insert(TABLE_NAME, null, cv);
        return rows != -1;
    }

    List<Employee> getEmployees(){
        List<Employee> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
//        c.moveToFirst();
        while(c.moveToNext()){
            list.add(new Employee(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)));
        }
        return list;
    }

    boolean updateEmployee(Employee emp){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, emp.getName());
        cv.put(COLUMN_CITY, emp.getCity());

        int rows = db.update(TABLE_NAME, cv, "ID = ?", new String[]{String.valueOf(emp.getId())});

        return rows != -1;
    }

    boolean deleteEmployee(Employee emp){
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(emp.getId())});
        return rows != -1;
    }
}
