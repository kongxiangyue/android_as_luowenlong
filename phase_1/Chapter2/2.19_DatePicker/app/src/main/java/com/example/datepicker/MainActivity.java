package com.example.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    static private int mDialogId = 999;

    @Override //APP初始化
    protected void onCreate(Bundle savedInstanceState) {
        // 界面加载XML配置
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定变量对View的引用 binding UI
        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();

        //取当前时间，完成初始化
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month + 1, day);
    }

    // Button响应函数, 点击后弹出设定时间对话框，并弹出Toast
    @SuppressWarnings("deprecation")// 不弹出安全的警告
    public void setDate(View view) {
        showDialog(mDialogId);
        Toast.makeText(getApplicationContext()
                , "ca", Toast.LENGTH_SHORT)
                .show();
    }


    @Override //  弹出设置时间的对话框
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == mDialogId) {
            return new DatePickerDialog(this
                    , myDateListener //设置监听器
                    , year, month, day);
        }
        return null;
    }

    // 实例化DatePickerDialog.OnDateSetListener类对象，作为监听器
    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override // 响应函数
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    // 将时间设置成TextView文字
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
