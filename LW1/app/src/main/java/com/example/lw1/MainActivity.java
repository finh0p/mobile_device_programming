package com.example.lw1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        setTitle("Test Task");
    }

    class DrawView extends View {
        Paint paint;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 104, 255);
            paint.setColor(Color.RED);
            canvas.drawRect(200, 105, 400, 200, paint);
            canvas.drawCircle(100, 200, 50, paint);
            paint.setStrokeWidth(10);
            float[] points = new float[] {300, 200, 400, 200};
            canvas.drawLines(points, paint);
            Path path = new Path();
            path.moveTo(50, 50);
            path.lineTo(0, 100);
            path.lineTo(100, 100);
            path.lineTo(50, 50);
            canvas.drawPath(path, paint);
            Path path2 = new Path();
            path2.addArc(new RectF(0, 0, 100, 100), 90, 180);
            canvas.drawPath(path2, paint);
        }
    }
}