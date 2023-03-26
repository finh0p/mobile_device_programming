package com.example.lw4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context) {
        super(context, "TrainBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS persons");
            db.execSQL(
                    "CREATE TABLE persons (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "last_name TEXT NOT NULL," +
                            "age INTEGER NOT NULL," +
                            "education TEXT NOT NULL," +
                            "passport TEXT NOT NULL" +
                    ");"
            );

        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persons");
        onCreate(db);
    }

    public ArrayList<Person> getPersonInfo() {
        ArrayList<Person> result = new ArrayList<Person>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query(
                    "persons",
                    new String[] {"id", "name", "last_name", "age", "education", "passport"},
                    null, null, null, null, null
            );
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Person p = new Person();
                    p.id = cursor.getInt(0);
                    p.name = cursor.getString(1);
                    p.last_name = cursor.getString(2);
                    p.age = cursor.getInt(3);
                    p.education = cursor.getString(4);
                    p.passport = cursor.getString(5);
                    result.add(p);
                }
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<Person>();
        }

    }

    public void readyTestData(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ArrayList<Person> persons = new ArrayList<Person>();
            persons.add(new Person(0, "Иван", "Иванов", 54, "primary", "1234123456"));
            persons.add(new Person(1, "Олег", "Хомуха", 43, "higher", "4563145345"));
            persons.add(new Person(2, "Евгений", "Довгань", 75, "secondary", "3453345345"));


            for (Person p : persons) {
                ContentValues cv = new ContentValues();
                cv.put("id", p.id);
                cv.put("name", p.name);
                cv.put("last_name", p.last_name);
                cv.put("age", p.age);
                cv.put("education", p.education);
                cv.put("passport", p.passport);
                db.insert("persons", null, cv);
            }
        } catch (Exception e) {
            return;
        }
    }
}
