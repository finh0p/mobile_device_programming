package com.example.lw6_indtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;

import kotlinx.coroutines.SchedulerTaskKt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Information", "Message");
        Log.e("FATAL ERROR", "ALL PROPALO!)");

        Timer timer = new Timer();
        TimerTask task = new TimerTask();
        timer.schedule(task, 1000);
    }

    public void click1(View v){
        Timer timer = new Timer();
        TimerTask task = new TimerTask();
        timer.schedule(task, 1000);
    }

    public void click2(View v){
        Intent intent = new Intent(this, ChronometerActivity.class);
        startActivity(intent);
    }
}