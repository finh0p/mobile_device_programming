package com.example.lw11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int N = 100;
    ArrayList<Integer> array;
    int indexOfFirsNegativeNumber = -1;
    int indexOfLastNegativeNumber = -1;
    ProgressBar sumNechBar;
    ProgressBar sumNegBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ((EditText) findViewById(R.id.elementCount)).setText(String.valueOf(N));
            sumNechBar = findViewById(R.id.sumNechBar);
            sumNegBar = findViewById(R.id.sumUnPosBar);
    }

    public void generateArray(int n){
        array = new ArrayList<Integer>();
        Random rnd = new Random();
        try {
            for (int i = 0; i < n; i++) {
                int number = rnd.nextInt(200) - 100;
                array.add(number);
                indexOfLastNegativeNumber = array.get(i) < 0 ? i : indexOfLastNegativeNumber;
            }
            for (int i = n - 1; i >=0; i--){
                indexOfFirsNegativeNumber = array.get(i) < 0 ? i : indexOfFirsNegativeNumber;
            }
            Log.d("Array", array.toString());
        } catch (Exception e) {
            Log.e("Gen Err", e.getMessage());
        }
    }

    public void calculateClick(View v){
        N = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.elementCount)).getText()));
        generateArray(N);
        sumNechBar.setVisibility(View.VISIBLE);
        Thread calcNechThread = new Thread(new Runnable() {
            int onePercent = N / 100;
            int sum = 0;
            @Override
            public void run(){
                for (int i = 1; i < N; i += 2){
                    sum += array.get(i);
                    sumNechBar.setProgress(sumNechBar.getProgress() + onePercent * 2);
                }
                sumNechBar.setProgress(100);
                ((EditText) findViewById(R.id.sumNechRes)).setText(String.valueOf(sum));
            }
        });
        Thread calcNegThread = new Thread(new Runnable() {
            int sum = 0;
            @Override
            public void run() {
                try{
                    int start = indexOfFirsNegativeNumber;
                    int stop = indexOfLastNegativeNumber;
                    Log.d("Debug in neg", String.format("Arguments \nStart = %d Stop = %d", start, stop));
                    if(start == -1 || stop == -1 || start == stop){
                        throw new Exception(String.format("Arguments error \nStart = %d Stop = %d", start, stop));
                    }
                    sumNegBar.setVisibility(View.VISIBLE);
                    int barStep = (stop - start) / 100;
                    for (int i = start + 1; i < stop; i++){
                        sum += array.get(i);
                        sumNegBar.setProgress(sumNegBar.getProgress() + barStep);
                    }
                    sumNegBar.setProgress(100);
                    ((EditText) findViewById(R.id.sumUnPosRes)).setText(String.valueOf(sum));
                } catch (Exception e) {
                        Log.e("Calculate Neg Sum Exception\n", e.getMessage());
                        ((EditText) findViewById(R.id.sumUnPosRes)).setText("Err");
                }
            }
        });
        calcNechThread.run();
        calcNegThread.run();
    }

}