package com.example.lenovo.ch06_02_boudservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Lenovo on 2017/4/5.
 */

public class LocalService extends Service {

    private final String TAG = "result";
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    public LocalService() {}

    public class LocalBinder extends Binder {
        //获得本地service对象，以便调用service中public方法
        public LocalService getLocalService(){
            Log.d(TAG,"getLocalService is executed ...");
            return LocalService.this;
        }
        /**
         * 模拟客户端要处理的LocalBinder中的public方法
         * @return
         */
        public  void sayHello(){
            Toast.makeText(getApplicationContext()
                    , "hello service"
                    , Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("result","onCreate executed");
    }

    /**
     * 绑定时调用
     * @param intent 传递过来的intent
     * @return 返回IBinder对象，传递给ServiceConnection实例处理
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("result","onBind executed");
        return mBinder;
    }

    /**
     * 解绑时调用
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("result","onUnbind executed");
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("result","onDestroy executed");
    }

    /**
     * 模拟客户端要处理的service中的public方法
     * @return
     */
    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }
}
