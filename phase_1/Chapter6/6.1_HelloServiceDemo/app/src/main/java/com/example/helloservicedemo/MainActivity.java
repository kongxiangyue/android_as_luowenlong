package com.example.helloservicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Method to start the service
    public void startService(View view) {
        //用intent启动service
        //注意这个service 在AndroidManifest里面注册了
        startService(new Intent(getBaseContext()
                , MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        //结束service
        //此时service的onDestroy方法被调用
        stopService(new Intent(getBaseContext()
                , MyService.class));
    }
}
