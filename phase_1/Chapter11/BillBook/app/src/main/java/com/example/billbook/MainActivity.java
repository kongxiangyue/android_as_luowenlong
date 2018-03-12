package com.example.billbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int OUT = 1;
    private static final int IN = 2;
    private DBManager mgr;
    private EditText etCount,etDescribe;
    private RadioGroup radioGroup;
    private TextView tvInfo;
    private Button btnAdd;
    private int countType = OUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化DBManager
        mgr = new DBManager(this);
        //绑定UI
        etCount = (EditText) findViewById(R.id.etCount);
        etDescribe = (EditText) findViewById(R.id.etDescribe);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        //"收入"/"支出"的切换
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {//用countType记录"收入"/"支出"的用户操作
                    case R.id.radioOut:
                        countType = OUT;
                        break;
                    case R.id.radioIn:
                        countType = IN;
                        break;
                }
            }
        });

        //"记一笔"的按键响应
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //总是将信息包装一下
                Count count = new Count();// 自定义的结构体

                // 将收支信息插入进去
                long time = System.currentTimeMillis();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = format.format(new Date(time));
                count.setDate(str);
                count.setMoney(Double.parseDouble(etCount.getText().toString()));
                count.setDescribe(etDescribe.getText().toString());
                count.setType(countType + "");
                mgr.insert(count);      //插入数值

                //汇总,将信息显示在"收支总览"
                resetInfo();
            }
        });
        resetInfo();
    }

    public void resetInfo(){
        // mgr负责将信息查询出来
        Double out = mgr.getResult(OUT);
        Double in = mgr.getResult(IN);

        //这里就是总收支
        Double all = in - out;
        // 将信息显示
        tvInfo.setText("总计支出："+out+"  总计收入："+in+" 结余：" +all+"。");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //应用的最后一个Activity关闭时应释放DB
        mgr.closeDB();
    }
}
