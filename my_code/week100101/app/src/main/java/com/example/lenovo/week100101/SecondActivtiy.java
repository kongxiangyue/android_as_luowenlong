package com.example.lenovo.week100101;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Lenovo on 2017/4/15.
 */

public class SecondActivtiy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String content = getIntent().getExtras().getString("msg");
        ((TextView)findViewById(R.id.textView2)).setText(content);
        

    }
}
