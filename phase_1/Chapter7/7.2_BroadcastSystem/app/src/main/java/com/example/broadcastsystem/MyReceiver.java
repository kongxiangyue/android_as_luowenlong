package com.example.broadcastsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //判断不同的广播进行不同的信息输出
        if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())) {
            Toast.makeText(context, "电量已恢复，可以使用!", Toast.LENGTH_LONG).show();
        } else if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {
            Toast.makeText(context, "电量过低，请尽快充电！", Toast.LENGTH_LONG).show();
        } else if (Intent.ACTION_DATE_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, "ACTION_DATE_CHANGED", Toast.LENGTH_LONG).show();
        } else if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(intent.getAction())) {
            String incoming_number = intent.getStringExtra("incoming_number");
            Toast.makeText(context, incoming_number, Toast.LENGTH_LONG).show();
        }
        //if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
        //    Bundle bundle = intent.getExtras();
        //    // 获取当前电量
        //    int current = bundle.getInt("level");
        //    // 获取总电量
        //    int total = bundle.getInt("scale");
        //    StringBuffer sb = new StringBuffer();
        //    sb.append("当前电量为：" + current * 100 / total + "%" + "  ");
        //    // 如果当前电量小于总电量的15%
        //    if (current * 1.0 / total < 0.15) {
        //        sb.append("电量过低，请尽快充电！");
        //    } else {
        //        sb.append("电量足够，请放心使用！");
        //    }
        //    Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
        //}
    }
}
