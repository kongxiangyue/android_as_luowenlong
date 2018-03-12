package com.example.lenovo.week180101;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lenovo on 2017/6/7.
 */

public class MyView extends View {

    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        {
            // 画一个点
            Paint paint = new Paint();
            paint.setStrokeWidth(10);
            canvas.drawPoint(10, 10, paint);
        }

        {
            // 画一条线
            Paint paint = new Paint();
            paint.setStrokeWidth(2);
            paint.setAntiAlias(true);
            paint.setARGB(255, 255, 0, 0);
            canvas.drawLine(10,60,200,40,paint);
        }

        {
            // 画一个矩形
            Paint paint = new Paint();
            paint.setStrokeWidth(2);
            paint.setARGB(255, 0, 255, 0);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(10, 65,200,100, paint);
        }

        {
            // 画一个圆形
            Paint paint = new Paint();
            paint.setStrokeWidth(2);
            paint.setARGB(255, 0, 0, 255);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(200, 200, 50,paint);
        }

        {
            // 画一个弧形
            Paint paint = new Paint();
            paint.setStrokeWidth(2);
            paint.setARGB(255, 100, 100, 255);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            RectF rect = new RectF(10,300,300,500);
            canvas.drawArc(rect, 30, 60, false, paint);
        }


        super.onDraw(canvas);
    }
}
