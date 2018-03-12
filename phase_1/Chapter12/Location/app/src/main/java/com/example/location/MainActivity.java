package com.example.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvLag,tvAlt,tvSpeed;
    private LocationManager lm = null;
    private Location mLocation;
    private MyLocationListner mLocationListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定ui
        tvLag = (TextView) findViewById(R.id.tvLag);
        tvAlt = (TextView) findViewById(R.id.tvAlt);
        tvSpeed = (TextView) findViewById(R.id.tvSpeed);

        // 获取LocationManager
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //进行初始化,其实就是将位置拿到,并显示，设置位置监听器
        initLocation();
    }
    private void initLocation(){

        //判断GPS是否正常启动
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(MainActivity.this
                    , "请开启GPS..."
                    ,Toast.LENGTH_SHORT);
            //用隐式启动的方法跳到GPS导航设置界面
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,0);
            return;
        }


        try {
            //先获取上一个位置信息
            mLocation = lm.getLastKnownLocation(lm.GPS_PROVIDER);
            // 将位置信息输出
            updateView(mLocation);
        } catch (SecurityException se){
        }

        //MyLocationListner用于回调获取位置信息
        if (mLocationListner == null) {
            mLocationListner = new MyLocationListner();
        }

        try{
            // 实现位置的实时更新
            // 设置手机位置的实时更新,当更新时由mLocationListner来处理
            // 3秒钟更新一次 最小位移变化 1米
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER
                    , 3000
                    , 1
                    , mLocationListner);
        } catch (SecurityException se) {
        }

    }

    private class MyLocationListner implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            // 当位置有变化时,直接更新显示信息
            updateView(location);
        }
        @Override
        public void onProviderDisabled(String provider)
        {
            // gps失效,则清空显示信息
            updateView(null);
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            try{//gps刚启动,则上一次的位置信息显示即可
                updateView(lm.getLastKnownLocation(provider));
            }catch (SecurityException e){}
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }
    }

    private void updateView(Location location)
    {
        if (location!=null) {// 更加程序健壮性
            tvLag.setText("当前经纬度：" + location.getLongitude() + "," + location.getLatitude());
            tvAlt.setText("当前海拔：" + location.getAltitude() + "m");
            tvSpeed.setText("当前速度：" + location.getSpeed() + "m/s");
        } else {
            tvLag.setText("当前经纬度：");
            tvAlt.setText("当前海拔：" );
            tvSpeed.setText("当前速度：");
        }
    }
}