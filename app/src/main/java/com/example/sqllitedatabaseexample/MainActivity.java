package com.example.sqllitedatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText name, phone, email, street, city, country;
    private Button save,display,row,delete,getAll;

    SqlLiteDatabaseExample sqlLite ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlLite = new SqlLiteDatabaseExample(this);


        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        street = findViewById(R.id.et_street);
        city = findViewById(R.id.et_city);
        country = findViewById(R.id.et_country);
        save = findViewById(R.id.bt_save);
        display = findViewById(R.id.bt_display);
        row = findViewById(R.id.bt_rows);
        delete = findViewById(R.id.bt_delete);
        getAll = findViewById(R.id.bt_getAll);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String street1 = street.getText().toString();
                String city1 = city.getText().toString();
                String country1 = country.getText().toString();


                sqlLite.insertData(name1, phone1, email1, street1, city1, country1);


            }
        });
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numRows = sqlLite.numOfRows();
                Toast.makeText(MainActivity.this, "No of Rows"+ numRows, Toast.LENGTH_SHORT).show();

            }
        });
        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> allData = sqlLite.getAllData();
                Toast.makeText(MainActivity.this, "Getting ALL The Data" + allData.get(1), Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite.deleteInformation(1);
                ArrayList<String> allData = sqlLite.getAllData();
                Toast.makeText(MainActivity.this, "Getting ALL The Data" + allData, Toast.LENGTH_SHORT).show();
            }
        });
    }
}