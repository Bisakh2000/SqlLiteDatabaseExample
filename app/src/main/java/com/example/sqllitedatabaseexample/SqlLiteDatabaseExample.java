package com.example.sqllitedatabaseexample;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//import androidx.annotation.Nullable;

public class SqlLiteDatabaseExample extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database";
    public static final String TABLE_NAME = "userData";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_COUNTRY = "country";


    public SqlLiteDatabaseExample(MainActivity context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table userData " +
                        "(id integer primary key, name text, phone text, email text, street text, city text, country text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String name1, String phone1, String email1, String street1, String city1, String country1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name1);
        cv.put("phone", phone1);
        cv.put("email", email1);
        cv.put("street", street1);
        cv.put("city", city1);
        cv.put("country", country1);

        db.insert("userData", null, cv);
        return true;
    }

    public int numOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numOfRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        return numOfRows;
    }

    public Integer deleteInformation(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("userData","id = ?",new String[]{Integer.toString(id)});
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllData(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from userData", null);
        c.moveToFirst();
        while(c.isAfterLast()== false){
            list.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
            c.moveToNext();
        }
        return  list;
    }
}
