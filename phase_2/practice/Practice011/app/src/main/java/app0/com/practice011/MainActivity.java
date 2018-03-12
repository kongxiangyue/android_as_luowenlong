package app0.com.practice011;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private WebView  webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView  = (TextView) findViewById(R.id.text);
        webView   = (WebView) findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/index.html");

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDefaultTextEncodingName("UTF-8");

        webView.addJavascriptInterface(this, "demo");

    }

    @JavascriptInterface
    public void login(String username, String password) {
        //Toast.makeText(this
        //        , username +"-" + password
        //        , Toast.LENGTH_LONG).show();
        checkLogin(username, password);

    }


    private void checkLogin(String username, String password) {

        String url = "http://192.168.3.4:5000/MyServer/Servlet/LoginServlet";
        final String parm = "username="+ username + "&" + "password=" + password;

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                PrintWriter out = null;
                BufferedReader in = null;
                String result = null;

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection
                            = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setUseCaches(false);

                    out = new PrintWriter(httpURLConnection.getOutputStream());
                    out.print(parm);


                    out.flush();// 这句话必须有

                    int responseCode = httpURLConnection.getResponseCode();

                    if (HttpURLConnection.HTTP_OK == responseCode) {
                        in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                        String line;
                        while ((line = in.readLine()) != null) {
                            result += line;
                        }
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return result;
            }

            @Override
            protected void onPostExecute(String s) {

                textView.setText(s);
                super.onPostExecute(s);
            }
        }.execute(url);



    }
}
