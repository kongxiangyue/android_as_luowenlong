package com.example.broadcastcustom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send= (Button)findViewById(R.id.button);
        //按键响应
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //发出一个com.example.action.DEMO_BROADCAST广播
                intent.setAction("com.example.action.DEMO_BROADCAST");
                //执行发出
                sendBroadcast(intent);
            }
        });
    }

}
