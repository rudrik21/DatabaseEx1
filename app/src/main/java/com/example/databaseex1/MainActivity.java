package com.example.databaseex1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String DATABASE_NAME = "myDatabase";
    DatabaseHelper db;

    private EditText edtName;
    private EditText edtCity;
    private Button btnAdd;

    List<Employee> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        edtName = (EditText) findViewById(R.id.edtName);
        edtCity = (EditText) findViewById(R.id.edtCity);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        //  MANUAL
        /*db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();*/

        showData();
    }

    private void showData() {
        //  MANUAL
        /*    String sql = "SELECT * FROM employees";
        Cursor c = db.rawQuery(sql, null);
//        c.moveToFirst();
        while (c.moveToNext()){
            String name = c.getString(1);
            String city = c.getString(2);

            Log.e("DATA: ", name + " -> " + city);
        }
        c.close();*/
        list.clear();
        list.addAll(db.getEmployees());
        Log.e("Employees: ", list.toString());
    }

    private void createTable() {
        //  MANUAL
        /* String sql = "CREATE TABLE IF NOT EXISTS employees(" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(200) NOT NULL, " +
                "city VARCHAR(200) NOT NULL)";
        db.execSQL(sql);*/


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd){
            addEmployee();
        }
    }

    private void addEmployee() {
        String name = edtName.getText().toString();
        String city = edtCity.getText().toString();

        //  MANUAL
        /*if (!name.isEmpty() && !city.isEmpty()) {
            String sql = "INSERT INTO employees(name, city) VALUES(?,?)";
            db.execSQL(sql, new String[]{name, city});
            Toast.makeText(this, "Employee added.. 🤟", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }*/

        if (!name.isEmpty() && !city.isEmpty()) {
            String sql = "INSERT INTO employees(name, city) VALUES(?,?)";
//            db.execSQL(sql, new String[]{name, city});
            Toast.makeText(this, "Employee added.. 🤟", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }
    }
}
