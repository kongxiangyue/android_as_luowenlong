package com.example.lenovo.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView textView = (TextView) findViewById(R.id.my_text);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                , RelativeLayout.LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.RIGHT_OF, R.id.centerRadioGroup);
        lp.addRule(RelativeLayout.BELOW, R.id.centerRadioGroup);


        textView.setLayoutParams(lp);
        textView.invalidate();
        onResume();




    }
}
