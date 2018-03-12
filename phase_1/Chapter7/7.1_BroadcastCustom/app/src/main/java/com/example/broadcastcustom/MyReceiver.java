package com.example.broadcastcustom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
//这个MyReceiver必须在manifest里面
//并指定接接受的是哪种广播
//,这里接收的自定义com.example.action.DEMO_BROADCAST
public class MyReceiver extends BroadcastReceiver {
    public MyReceiver(){}
    @Override
    public void onReceive(Context context, Intent intent) {

        //
        //在接收到广播后,输出一个Toast
        Toast.makeText(context,"Intent Detected."
                ,Toast.LENGTH_LONG).show();
    }
}
