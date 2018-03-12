package com.example.file;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cqupt.test7_2.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends Activity {

    private Button btnSave,btnRead,btnSaveSD,btnReadSD;
    private EditText edFilename,edFilecontent;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI绑定
        btnSave = (Button)this.findViewById(R.id.btnSave);
        btnRead = (Button)this.findViewById(R.id.btnRead);
        btnSaveSD = (Button)this.findViewById(R.id.btnSaveSD);
        btnReadSD = (Button)this.findViewById(R.id.btnReadSD);
        edFilename = (EditText)this.findViewById(R.id.edFilename);
        edFilecontent = (EditText)this.findViewById(R.id.edFilecontent);

        //保存的响应
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //获取文件与内容
                String filename = edFilename.getText().toString();
                String filecontent = edFilecontent.getText().toString();
                FileOutputStream out = null;
                try {
                    // 使用out对象进行将内容保存到文件当中
                    out = context.openFileOutput(filename
                            , Context.MODE_PRIVATE);
                    out.write(filecontent.getBytes("UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    try {
                        // 将out对象文件关闭
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }});

        // 保存至sd卡的响应
        btnSaveSD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String filename = edFilename.getText().toString();
                String filecontent = edFilecontent.getText().toString();

                // 开始存储
                // 首先判断sd卡存不存在
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                    // 以完整路径为参数打开一个文件
                    File file = new File(Environment.getExternalStorageDirectory().toString()
                                + File.separator+"cqupt"+File.separator + filename ); //定义存储路径
                    // 判断文件存不存在
                    if(!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();  //不存在则 创建文件夹
                    }

                    PrintStream out = null;
                    try{
                        // 使用PrintStream 写入内容
                        out = new PrintStream(new FileOutputStream(file,true));
                        out.println(filecontent);   //写入内容
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        if (out!= null){
                            // 写完后，将文件关掉
                            out.close();
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "SD卡不存在，存储失败", Toast.LENGTH_SHORT).show();
                }
            }});

        // 读取的响应
        btnRead.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 文件名
                String filename = edFilename.getText().toString(); //获得读取的文件的名称
                FileInputStream in = null;

                // ByteArrayOutputStream对象与byte数组的关系
                ByteArrayOutputStream bout = null;
                // 1024个字节数组
                byte[]buf = new byte[1024];
                bout = new ByteArrayOutputStream();
                int length = 0;
                try {
                    //打开文件
                    in = context.openFileInput(filename); //获得输入流
                    // 每次最多只读1024字节
                    while((length=in.read(buf))!=-1){
                        // 读出来的字节暂存到bout
                        bout.write(buf,0,length);
                    }
                    // 将bout里面的字节转化为字符串
                    byte[] content = bout.toByteArray();
                    edFilecontent.setText(new String(content,"UTF-8")); //设置文本框为读取的内容
                } catch (Exception e) {
                    e.printStackTrace();
                }
                edFilecontent.invalidate(); //刷新屏幕
                try{
                    // 关闭文件
                    in.close();
                    bout.close();
                }
                catch(Exception e){}
            }});


        // 读取sd里里面文件的响应
        btnReadSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = edFilename.getText().toString(); //获得读取的文件的名称
                String filecontent = "";
                //  以完整路径为参数，打开这个文件
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File file = new File(Environment.getExternalStorageDirectory().toString()
                        + File.separator + "cqupt" + File.separator + filename);

                    // 如果不存在则创建
                    if (!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }
                    //使用Scanner对象一行行地读取sd卡文件里面的内容
                    Scanner scan = null;
                    try{

                        scan = new Scanner(new FileInputStream(file));
                        while(scan.hasNext()){
                            filecontent += scan.next()+"\n";
                        }
                        // 内容读取之后，将内容显示出来
                        edFilecontent.setText(filecontent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if (scan!=null){
                            // 将文件关闭
                            scan.close();
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this, "SD卡不存在，存储失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
