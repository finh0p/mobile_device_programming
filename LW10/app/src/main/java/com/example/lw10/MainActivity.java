package com.example.lw10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.DateInterval;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Calendar dateAndTime = Calendar.getInstance();
    WifiManager wifiManager;
    Timer timer;
    EditText startTime, stopTime;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        Log.d("Wifi", String.valueOf(wifiManager.isWifiEnabled()));
        startTime = ((EditText)findViewById(R.id.startTime));
        stopTime = ((EditText)findViewById(R.id.stopTime));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String time1 = String.valueOf(startTime.getText());
                String time2 = String.valueOf(stopTime.getText());

                if(
                        !time1.isEmpty() &&
                        !time2.isEmpty()
                ) {
                    try {
                        int hours1 = Integer.parseInt(time1.split(".")[0]);
                        int minutes1 = Integer.parseInt(time1.split(".")[1]);
                        int hours2 = Integer.parseInt(time2.split(".")[0]);
                        int minutes2 = Integer.parseInt(time2.split(".")[1]);
                        String curTime = DateUtils.formatDateTime(MainActivity.this, (new Date()).getTime(),
                                DateUtils.FORMAT_ABBREV_TIME | DateUtils.FORMAT_SHOW_TIME);
                        int hours = Integer.parseInt(curTime.split(".")[0]);
                        int minutes = Integer.parseInt(curTime.split(".")[1]);
                        if (hours1 < hours && hours < hours2){
                            wifiManager.setWifiEnabled(true);
                        } else if (hours1 == hours && hours == hours2 && minutes1 < minutes && minutes < minutes2) {
                            wifiManager.setWifiEnabled(true);
                        } else {
                            wifiManager.setWifiEnabled(false);
                        }
                    } catch (Exception err){
                        Log.e("Error", err.getMessage());
                        return;
                    }
                }
            }
        }, 60000, 60000);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "name";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ch-1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(getApplicationContext(), "ch-1")
                .setContentTitle("Title")
                .setContentText("Notification text")
                .setSmallIcon(R.drawable.ic_launcher_background);
        NotificationManager mgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mgr.notify(1, nbuilder.build());


        ((EditText) findViewById(R.id.startTime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        dateAndTime.set(Calendar.MINUTE, minute);
                        changeStartTime();
                    }
                };
                TimePickerDialog dlg = new TimePickerDialog(MainActivity.this, t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true);
                dlg.show();
            }
        });

        ((EditText) findViewById(R.id.stopTime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        dateAndTime.set(Calendar.MINUTE, minute);
                        changeStopTime();
                    }
                };
                TimePickerDialog dlg = new TimePickerDialog(MainActivity.this, t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true);
                dlg.show();
            }
        });
    }

    protected void changeStartTime(){
        ((EditText)findViewById(R.id.startTime)).setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_ABBREV_TIME | DateUtils.FORMAT_SHOW_TIME));
    }

    protected void changeStopTime(){
        ((EditText)findViewById(R.id.stopTime)).setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_ABBREV_TIME | DateUtils.FORMAT_SHOW_TIME));
    }
}