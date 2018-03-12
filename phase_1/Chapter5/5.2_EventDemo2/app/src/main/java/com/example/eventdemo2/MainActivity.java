package com.example.eventdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity
        extends Activity
        implements View.OnClickListener {//必须继承OnClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--- find both the buttons---
        Button sButton = (Button) findViewById(R.id.button);
        Button lButton = (Button) findViewById(R.id.button2);


        // -- register click event with first button ---
        sButton.setOnClickListener(this);
        // -- register click event with second button ---
        lButton.setOnClickListener(this);//是因为实现View.OnClickListener

    }

    //--- Implement the OnClickListener callback
    public void onClick(View v) {//两按键点击时，都会响应这个函数
        if (v.getId() == R.id.button) {//判断响应的来源
            // 然后执行处理程序
            // --- find the text view --
            TextView txtView = (TextView) findViewById(R.id.textView);
            // -- change text size --
            txtView.setTextSize(14);
            return;//这个也是很重要，表示下面的程序不再执行
        }
        if (v.getId() == R.id.button2) {
            // --- find the text view --
            TextView txtView = (TextView) findViewById(R.id.textView);
            // -- change text size --
            txtView.setTextSize(24);
            return;
        }
    }
}
