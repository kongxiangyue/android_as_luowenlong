package com.example.locationprovider;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.cqupt.test8_1.R;

import java.util.List;

public class ProviderList extends AppCompatActivity {
    LocationManager lm;
    ListView lvProviderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list);

        //先获取LocationManager
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        lvProviderList = (ListView)findViewById(R.id.listViewProvider);

        //LocationProvider lp = lm.getProvider(LocationManager.GPS_PROVIDER);


        //在模拟器运行时是获取不到的
        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(false);
        String provider = lm.getBestProvider(criteria, true);
        Toast.makeText(this, provider, Toast.LENGTH_SHORT).show();

        //通过LocationManager 一次性获取 所有的Provider
        List<String> providerList = lm.getAllProviders();



        //providerList.add(provider);
//
        // 将Provider的名称通过ListView显示出来
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this
                , android.R.layout.simple_expandable_list_item_1
                , providerList);
        lvProviderList.setAdapter(adapter);
    }
}
