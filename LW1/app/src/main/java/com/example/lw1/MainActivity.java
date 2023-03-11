package com.example.lw1;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.content.Context;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class DrawView extends View {
        Paint paint;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            rect = new Rect();
        }
    }
}