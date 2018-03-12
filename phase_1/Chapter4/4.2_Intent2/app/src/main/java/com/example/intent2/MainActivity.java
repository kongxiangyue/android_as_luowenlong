package com.example.intent2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //各个响应程序
        b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //查看http类据-->打开浏览器
                //由于CustomActivity注册了http类型，所以它也去响应这个类型的查看
                Intent i = new Intent(android.content.Intent.ACTION_VIEW
                        ,Uri.parse("http://www.baidu.com"));
                startActivity(i);
            }
        });

        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //用自定的action 去查看http数据类型，
                // 这个在manifests里面需要注册
                // 并且category.DEFAULT
                Intent i = new Intent("com.example.intent2.LAUNCH"
                        , Uri.parse("http://www.baidu.com"));
                startActivity(i);
            }
        });

        b3=(Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //跟上面的响应基本一致
                //会崩溃的原因在于注册的scheme不是https
                Intent i = new Intent("com.example.intent2.LAUNCH"
                        , Uri.parse("https://www.baidu.com"));
                startActivity(i);
            }
        });




        //SharedPreferences share = getSharedPreferences("preference1"
        //        ,Activity.MODE_WORLD_READABLE);
        //int i = share.getInt("age",0);




    }
}
