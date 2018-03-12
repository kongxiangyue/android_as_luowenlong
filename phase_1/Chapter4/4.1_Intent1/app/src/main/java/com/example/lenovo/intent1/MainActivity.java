package com.example.lenovo.intent1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);
        //按键响应
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //隐式启动 没填component
                //打开浏览器
                Intent i = new Intent(android.content.Intent.ACTION_VIEW
                        , Uri.parse("http://www.baidu.com"));//data 类型
                startActivity(i);
            }
        });

        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐式启动 用action启动
                //打开拨号界面
                Intent i = new Intent(android.content.Intent.ACTION_VIEW
                        , Uri.parse("tel:18611111186"));
                startActivity(i);
            }
        });
    }
}
