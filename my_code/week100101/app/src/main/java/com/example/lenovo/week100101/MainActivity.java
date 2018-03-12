package com.example.lenovo.week100101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText2);
                Intent intent = new Intent(MainActivity.this
                        , SecondActivtiy.class);

                String content = null;
                content = editText.getText().toString();
                if (content != null) {
                    intent.putExtra("msg", content);
                    startActivity(intent);
                }


            }
        });
    }
}
