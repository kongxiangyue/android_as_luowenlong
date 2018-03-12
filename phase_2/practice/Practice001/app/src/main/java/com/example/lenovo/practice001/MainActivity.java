package com.example.lenovo.practice001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox ch1 = (CheckBox) findViewById(R.id.checkBox);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = ch1.isChecked()
                        ? "被选中"
                        : "没被选中";

                Toast.makeText(MainActivity.this
                        , "checkbox 1 " + msg
                        , Toast.LENGTH_LONG).show();
            }
        });

    }
}
