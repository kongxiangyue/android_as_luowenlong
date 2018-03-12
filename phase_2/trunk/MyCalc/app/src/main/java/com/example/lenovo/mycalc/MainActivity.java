package com.example.lenovo.mycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator = new Calculator();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_panel3);
        textView = (TextView) findViewById(R.id.textView2);
    }

    public void btnClick(View v) {
        calculator.append(((Button)v).getText().toString());
        display(false);
    }

    public void operaClick(View v) {
        char oper = ((Button)v).getText().toString().charAt(0);
        Double result = calculator.dealWithOperatin(oper);
        if ('=' == oper) {
            textView.setText(result.toString());
        } else {
            display(true);
        }

    }

    private void display(boolean isClear) {
        if (isClear) {
            textView.setText("0.0");
        } else {
            textView.setText(calculator.getCurrentNum());
        }
    }



}