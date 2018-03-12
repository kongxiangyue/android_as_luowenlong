package com.example.lenovo.ch06_02_boudservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
* 本APP的核心功能：
* */

public class MainActivity extends AppCompatActivity {
    private final String TAG = "result";
    private boolean mBound = false;
    private LocalService mLocalService;
    // binder的作用，Activity 与 Service的桥梁
    private LocalService.LocalBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        // 在启动时
        super.onStart();

        Intent intent = new Intent(this, LocalService.class);
        // 绑定服务（此时服务启动）
        bindService(intent
                , mConnection //绑定监听器
                , BIND_AUTO_CREATE);
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        // 执行该方法表示绑定成功，并可以调用服务了。
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取LocalBinder对象，同时获取LocalService
            binder = (LocalService.LocalBinder)service;
            mLocalService = binder.getLocalService();
            mBound = true;
        }
        // 绑定失败调用下面的方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected...");
            mBound = false;
        }
    };

    public void onButtonClick(View v){
        switch (v.getId()){
            // 调用service方法
            case R.id.btn_call_method:
                // 若耗时则应在一个新的线程中运行
                if(mBound){
                    Toast.makeText(this,
                            mLocalService.getRandomNumber() + "", Toast.LENGTH_LONG).show();
                    binder.sayHello();
                }
                break;
            // 解绑
            case R.id.btn_bind:
                if(mBound){
                    unbindService(mConnection);
                    mBound = false;
                }
                break;
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        // 解绑
        if(mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }
}
