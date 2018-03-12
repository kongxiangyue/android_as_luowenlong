package com.example.lenovo.week100201;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Lenovo on 2017/4/15.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        ImageView iv = (ImageView) findViewById(R.id.imageView3);
        TextView tx  = (TextView) findViewById(R.id.textView);

        iv.setImageResource(getIntent().getExtras().getInt("imgid"));
        tx.setText(getIntent().getExtras().getString("msg"));

    }
}
