package com.example.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickAddName(View view) {//添加记录
        // Add a new student record
        //将编辑框的内容存入
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());
        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        //向特定uri存入内容
        Uri uri = getContentResolver().insert(//执行添加
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {//检索现有的记录

        // Retrieve student records
        String URL = StudentsProvider.URL;


        // 无论存入或取出都是以这个uri为操作目标的
        Uri students = Uri.parse(URL);
        // 通过uri检索所有的记录//执行查询
        Cursor c = managedQuery(students, null, null, null, "name");

        //得到结果然后打印出来
        if (c.moveToFirst()) {
            do{// do while是为应对多条数据的情况，将每条都遍历出来
                Toast.makeText(this,
                        //每列的数据内容取出,然后打印
                        c.getString(c.getColumnIndex(StudentsProvider._ID))
                                + ", "
                                + c.getString(c.getColumnIndex(StudentsProvider.NAME))
                                + ", "
                                + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
