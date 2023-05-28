package com.example.lw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLHelper(this);
        db.readyTestData();

        LinearLayout l1 = (LinearLayout)findViewById(R.id.l1);
        LinearLayout row;

        ArrayList<Person> persons = db.getPersonInfo();
        for (Person p: persons) {
            row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView id = new TextView(this);
            id.setText(Integer.toString(p.id));
            id.setWidth(120);

            TextView name = new TextView(this);
            name.setText(p.name);
            name.setWidth(120);

            TextView last_name = new TextView(this);
            last_name.setText(p.last_name);
            last_name.setWidth(120);

            TextView age = new TextView(this);
            age.setText(Integer.toString(p.age));
            age.setWidth(120);

            TextView education = new TextView(this);
            education.setText(p.education);
            education.setWidth(120);

            TextView passport = new TextView(this);
            passport.setText(p.passport);
            passport.setWidth(120);;

            row.addView(id);
            row.addView(name);
            row.addView(last_name);
            row.addView(age);
            row.addView(education);
            row.addView(passport);

            l1.addView(row);
        }
    }
}