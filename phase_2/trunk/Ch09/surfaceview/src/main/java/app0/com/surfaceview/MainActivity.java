package app0.com.surfaceview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnPause;
    private Button btnStop;
    private SurfaceView surfaceView;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.button);
        btnPause = (Button) findViewById(R.id.button2);
        btnStop = (Button) findViewById(R.id.button3);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        mediaPlayer = new MediaPlayer();
        surfaceView.getHolder().setKeepScreenOn(true);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                play();

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });

    }

    private void play() {
        //0-->start
        //pause-->start
        //stop-->start
        mediaPlayer.reset();

        Uri uri = Uri.parse("mnt/shared/Other/Test1.mp4");

        try {
            mediaPlayer.setDataSource(MainActivity.this, uri);

            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected  void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}
