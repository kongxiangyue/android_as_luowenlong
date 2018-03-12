package app0.com.urlconn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText edName, edPass;
    Button btnLogin;
    TextView txLoginRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checkLogin();
                checkLogin2();
            }
        });

    }

    private void bindUI() {
        edName = (EditText) findViewById(R.id.editText);
        edPass = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.button);
        txLoginRes = (TextView) findViewById(R.id.textView2);
    }

    private void checkLogin() {
        //ip 需要变换，根据自己的机子实际情况
        final String url = "http://192.168.3.4:5000/MyServer/Servlet/LoginServlet";
        String username = edName.getText().toString();
        String password = edPass.getText().toString();
        final String param = "username=" + username + "&"
                + "password=" + password;

        new AsyncTask<String, Void, String>() {
            PrintWriter out;
            BufferedReader in = null;
            String result;

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    URLConnection conn = url.openConnection();

                    conn.setRequestProperty("accept", "/*");  //设置URLConnection参数
                    conn.setRequestProperty("connection", "Keep-Alive");
                    conn.setRequestProperty("user-agent",
                            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                    conn.setDoInput(true);     //允许输入
                    conn.setDoOutput(true);    //允许输出

                    out = new PrintWriter(conn.getOutputStream());
                    out.print(param);
                    out.flush();// 这句话在书本上是少写了，补上去

                    in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

                    String line;
                    while ((line =in.readLine()) != null) {
                        result += line;
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
                    txLoginRes.setText("验证结果：" + result);
                }
                super.onPostExecute(result);

            }
        }.execute(url);


    }

    void checkLogin2() {
        //ip 需要变换，根据自己的机子实际情况
        final String url = "http://192.168.3.4:5000/MyServer/Servlet/LoginServlet";
        String username = edName.getText().toString();
        String password = edPass.getText().toString();
        final String param = "username=" + username + "&"
                + "password=" + password;

        new AsyncTask<String, Void, String>() {
            PrintWriter out;
            BufferedReader in = null;
            String result;

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
                    txLoginRes.setText("验证结果：" + result);
                }
                super.onPostExecute(result);

            }
        }.execute(url);
    }
}
