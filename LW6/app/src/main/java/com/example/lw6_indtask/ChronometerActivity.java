package com.example.lw6_indtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import java.util.Random;

public class ChronometerActivity extends AppCompatActivity {

    Chronometer chronometer;
    int seconds = 0;
    int a = 13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        // Start Individual Task
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Random rnd = new Random();
                int b = rnd.nextInt();
                int x = -30 + seconds;
                seconds = (seconds + 1) % 60;
                if (x < 0 && b != 0){
                    Log.i("Ur1", "x = " + x + " b = " + b + " Res = " + 13 * x * x + b);
                } else if (x > 0 && b == 0) {
                    Log.i("Ur2", "x = " + x + " b = " + b + " Res = " + (x - a)/ (x - rnd.nextInt()) );
                } else {
                    Log.i("Ur3", "x = " + x + " b = " + b + " Res = " + x / rnd.nextInt() );
                }
            }
        });
        // End Individual Task
    }

    public void start(View v){
        chronometer.start();
    }

    public void stop(View v){
        chronometer.stop();
    }

    public void clear(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        seconds = 0;
    }
}