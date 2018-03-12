package com.example.practice005;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String FILENAME = "info";

    private EditText edString     = null;
    private EditText edInt        = null;
    private EditText edFloat      = null;
    private CheckBox checkBoolean = null;
    private Button  btnSave       = null;

    private TextView txString  = null;
    private TextView txInt     = null;
    private TextView txFloat   = null;
    private TextView txBoolean = null;
    private SharedPreferences sharedPreferences;

    private String keyString  = "keyString";
    private String keyInt     = "keyInt";
    private String keyFloat   = "keyFloat";
    private String keyBoolean = "keyBoolean";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences
                = super.getSharedPreferences(FILENAME, MODE_PRIVATE);

        bindUI();
        dispSaving();
        setSaving();

        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "带图片的Toast", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();


                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.mipmap.ic_launcher);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
        });

    }

    private void bindUI() {

        edString     = (EditText)findViewById(R.id.editText);
        edInt        = (EditText)findViewById(R.id.editText2);
        edFloat      = (EditText)findViewById(R.id.editText3);
        checkBoolean = (CheckBox)findViewById(R.id.checkBox);
        btnSave      = (Button)findViewById(R.id.button);
        txString     = (TextView)findViewById(R.id.textView);
        txInt        = (TextView)findViewById(R.id.textView2);
        txFloat      = (TextView)findViewById(R.id.textView3);
        txBoolean    = (TextView)findViewById(R.id.textView4);

    }

    private void dispSaving() {
        String valString   = sharedPreferences.getString(keyString, "未定义");
        int    valInt      = sharedPreferences.getInt(keyInt, 0);
        float  valFloat    = sharedPreferences.getFloat(keyFloat, 0);
        boolean valBoolean = sharedPreferences.getBoolean(keyBoolean, false);

        txString.setText("字符串是：" + valString);
        txInt.setText("整型是：" + valInt);
        txFloat.setText("浮点型是：" + valFloat);
        txBoolean.setText("是否被选中的保存结果" + valBoolean);

    }

    private void setSaving() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saving();

            }
        });
    }

    private void saving() {
        String valString   = edString.getText().toString();
        int    valInt      = Integer.parseInt(edInt.getText().toString());
        float  valFloat    = Float.parseFloat(edFloat.getText().toString());
        boolean valBoolean = checkBoolean.isChecked();

        SharedPreferences.Editor editor
                = sharedPreferences.edit();

        editor.putString(keyString, valString);
        editor.putInt(keyInt, valInt);
        editor.putFloat(keyFloat, valFloat);
        editor.putBoolean(keyBoolean, valBoolean);

        editor.commit();

    }
}
