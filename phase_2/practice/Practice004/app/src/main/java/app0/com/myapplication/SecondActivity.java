package app0.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/22.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Bundle bundle = getIntent().getExtras();

        String strVal = bundle.getString("key1");
        int    intVal = bundle.getInt("key2");

        TextView tx = (TextView)findViewById(R.id.textView3);

        tx.setText("字符串为：" + strVal
                + " 整型为：" + intVal);


    }

}
