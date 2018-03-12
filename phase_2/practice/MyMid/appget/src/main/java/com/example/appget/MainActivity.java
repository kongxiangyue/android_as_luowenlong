package com.example.appget;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);

        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();

            }
        });
    }


    private void getInfo() {

        final String url = "http://172.16.33.191:5000/for_get";
        final String param = "id=123&name=张三";

        new AsyncTask<String, Void, String>() {
            PrintWriter out;
            BufferedReader in = null;
            String result = "";

            @Override
            protected String doInBackground(String... strings) {
                try {

                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setRequestProperty("accept", "/*");  //设置URLConnection参数
                    conn.setRequestProperty("connection", "Keep-Alive");
                    conn.setRequestProperty("user-agent",
                            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                    conn.setDoInput(true);     //允许输入
                    conn.setDoOutput(true);    //允许输出

                    conn.setRequestMethod("POST");
                    conn.setUseCaches(false);

                    out = new PrintWriter(conn.getOutputStream());
                    out.print(param);
                    out.flush();// 这句话在书本上是少写了，补上去


                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        in = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                        String line;
                        while ((line =in.readLine()) != null) {
                            result += line;
                        }
                    }

                    return  result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    textView.setText("从服务器接收的信息：" + result);
                }
                super.onPostExecute(result);

            }
        }.execute(url);


    }

}
