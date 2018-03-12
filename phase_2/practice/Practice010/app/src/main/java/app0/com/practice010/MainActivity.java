package app0.com.practice010;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView tx;
    private Button   btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = (TextView)findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer();
            }
        });


    }

    private void connectServer() {
       new  AsyncTask<String, Void, String>() {


           @Override
           protected String doInBackground(String... strings) {
               String result = "";

               try {
                   Socket socket = new Socket(strings[0], 4666);
                   socket.setSoTimeout(3000);

                   BufferedReader br
                           = new BufferedReader(
                           new InputStreamReader(socket.getInputStream()));

                   result = br.readLine();

                   br.close();
                   socket.close();





               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   return result;
               }
           }

           @Override
           protected void onPostExecute(String result) {
               tx.setText(result);
               super.onPostExecute(result);
           }
       }.execute("192.168.3.4");
    }
}
