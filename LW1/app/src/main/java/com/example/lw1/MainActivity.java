package com.example.lw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.content.Context;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
//        textView.setText(R.string.TextView);
//        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        setTitle("Kirill Budko");

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.addView(textView);
//        constraintLayout.addView(new DrawView(this));
//        setContentView(constraintLayout);
//        setContentView(new DrawView(this));
        constraintLayout.addView(new MyDrawView(this));
        setContentView(constraintLayout);
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

    class MyDrawView extends View {

        Paint paint;
        Rect rect;

        public MyDrawView(Context context){
            super(context);
            paint = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(Color.RED);
//  Task 3
//            paint.setAlpha(128);
//            DisplayMetrics metrics = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//            float height = metrics.heightPixels * 0.95f;
//            float width = metrics.widthPixels;
//            canvas.drawOval(0.0f, 0.0f, width, height, this.paint);


            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            float height = metrics.heightPixels * 0.95f;
            float width = metrics.widthPixels;
            paint.setColor(Color.BLUE);
            canvas.drawRect(0, 0, width, height/2, paint);
            paint.setColor(Color.GREEN);
            canvas.drawRect(0, height / 2, width, height, paint);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);
            canvas.drawLines(new float[]{0f, height, width/2, height/2}, paint);
            canvas.drawLines(new float[]{0f + 50f, height, width/2 + 50, height/2}, paint);
            float currentX = 10;
            float currentY = height;
            float endY = height/2;
            float step = 40;
            while (currentY > endY) {
                canvas.drawLines(new float[]{currentX - 20, currentY, currentX + 70, currentY - 30}, paint);
                currentY -= step;
                currentX += step / 2;
            }
        }
    }
}