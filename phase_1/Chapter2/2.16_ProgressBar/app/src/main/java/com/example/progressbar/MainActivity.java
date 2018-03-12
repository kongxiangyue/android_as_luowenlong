package com.example.progressbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Button b1;
    private ProgressDialog progress;
    private ProgressBar mProgress;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button2);
        mProgress = (ProgressBar) findViewById(R.id.progressBar4);
        mProgress.setMax(100);
        //mProgress.setProgress(80);


        mHandler = new Handler() {
            int mJumpTime = 0;

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if (mJumpTime < 100) {

                            mJumpTime += 5;
                        progress.setProgress(mJumpTime);

                    }
                    super.handleMessage(msg);
                }
            }
        };
    }
    public void download(View view){
        progress=new ProgressDialog(this);
        progress.setMessage("Downloading Music");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        //progress.incrementProgressBy(95);
        progress.show();
        int k = progress.getProgress();
        int i = 0;

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        sleep(2000);
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}
