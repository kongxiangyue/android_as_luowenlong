package com.example.helloservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        //不会被运行到
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        //在启动会被调用
        Toast.makeText(this
                , "Service Started"
                , Toast.LENGTH_LONG).show();
        return START_STICKY;//被kill掉，会自动重启
    }
    @Override
    public void onDestroy() {
        // service结束时被调用
        super.onDestroy();
        Toast.makeText(this
                , "Service Destroyed"
                , Toast.LENGTH_LONG).show();
    }
}
