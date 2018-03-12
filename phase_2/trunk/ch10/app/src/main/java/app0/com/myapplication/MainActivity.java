package app0.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView textView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        new Thread() {
            public void run() {

                try {
                    Socket socket = new Socket("192.168.3.4", 4666);
                    socket.setSoTimeout(3000);

                    BufferedReader br
                            = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String line = br.readLine();

                    textView.setText("Client received:" + line);

                    br.close();
                    socket.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }.start();

    }
}
