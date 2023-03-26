package com.example.lw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean q1IsActive = false, q2IsActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button)findViewById(R.id.b1);
        b1.setText("Новый текст");
    }

    public void onButtonClick1(View v) {
        Toast.makeText(MainActivity.this, "Будко", Toast.LENGTH_SHORT).show();
        if (this.getTitle() == "Будко Кирилл") {
            this.setTitle("А еще чего?");
        } else {
            this.setTitle("Будко Кирилл");
        }
    }

    public void onButtonClick2(View v) {
        Button b = (Button)v;
        if (b.getId() == R.id.b2) {
            if (b.getText() == "Будко") {
                b.setText("Нажми ещё");
            } else {
                b.setText("Будко");
            }
        } else {
            if (b.getText() != "Будко") {
                b.setText("Будко");
            } else {
                switch (b.getId()) {
                    case R.id.b3: b.setText("Кнопка 3"); break;
                    case R.id.b4: b.setText("Кнопка 4"); break;
                    case R.id.b5: b.setText("Button 5"); break;
                }
            }
        }
    }

    public void qButton(View v) {
        Button b = (Button)v;

        // Ни одна кнопка не активирована (т.е. программа только запустилась)
        if (!this.q1IsActive && !this.q2IsActive){
            b.setText("Будко");
            switch (b.getId()) {
                case R.id.bq1: this.q1IsActive = true; break;
                case R.id.bq2: this.q2IsActive = true; break;
            }
        // Если хотябы ода из кнопок нажата
        } else {
            if (this.q1IsActive && b.getId() == R.id.bq2) {
                b.setText("Будко");
                ((Button)findViewById(R.id.bq1)).setText("Можно нажать");
                this.q1IsActive = false;
                this.q2IsActive = true;
            } else if (this.q2IsActive && b.getId() == R.id.bq1) {
                b.setText("Будко");
                ((Button)findViewById(R.id.bq2)).setText("Можно нажать");
                this.q1IsActive = true;
                this.q2IsActive = false;
            }
        }
    }
    public void onButtonClick3(View v) {
        Button b = (Button)v;
        b.setText("Будко");
    }

    public void onButtonClick4(View v) {
        Button b = (Button)v;
        b.setText("Будко");
    }

    public void onButtonClick5(View v) {
        Button b = (Button)v;
        b.setText("Будко");
    }
    
}