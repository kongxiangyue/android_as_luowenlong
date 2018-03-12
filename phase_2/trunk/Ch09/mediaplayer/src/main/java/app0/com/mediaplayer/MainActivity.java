package app0.com.mediaplayer;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {
    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private Button btnRestart;
    private TextView tvPlayStat;
    private SeekBar seekBar;

    private MediaPlayer mediaPlayer;
    private boolean playFlag  = false;
    private boolean pauseFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();

        mediaPlayer = MediaPlayer.create(MainActivity.this
                , R.raw.my_music);

        tvPlayStat.setText(getResources().getString(R.string.state_prepare));


    }

    private void bindUI() {
        btnPlay    = (Button) findViewById(R.id.btnPlay);
        btnPause   = (Button) findViewById(R.id.btnPause);
        btnStop    = (Button) findViewById(R.id.btnStop);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        tvPlayStat = (TextView) findViewById(R.id.tvPlayState);
        seekBar    = (SeekBar) findViewById(R.id.seekBar);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnRestart.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay    : handlePlay(); break;
            case R.id.btnPause   : handlePause(); break;
            case R.id.btnStop    : handleStop(); break;
            case R.id.btnRestart : handleRestart(); break;
            default: break;

        }
    }


    private void handlePlay() {
        // start
        // pause -->start
        // stop  -->start
        if (false == pauseFlag) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    tvPlayStat.setText(getResources().getString(R.string.state_stop));
                    mediaPlayer.release();
                }
            });

            playFlag = true;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            tvPlayStat.setText(getResources().getString(R.string.state_playing));

            seekBar.setMax(mediaPlayer.getDuration());
            UpdateSeekbar updateSeekbar = new UpdateSeekbar();
            updateSeekbar.execute(1000);
            seekBar.setOnSeekBarChangeListener(new MySeekbarChangeListner());
        }
        else {
            pauseFlag = false;
            playFlag   = true;
            mediaPlayer.start();
            tvPlayStat.setText(getResources().getString(R.string.state_playing));
        }




    }
    private void handlePause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playFlag = false;
            pauseFlag = true;
            tvPlayStat.setText(getResources().getString(R.string.state_pause));

        }
    }
    private void handleStop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            playFlag = false;

            tvPlayStat.setText(getResources().getString(R.string.state_stop));

        }
    }
    private void handleRestart() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        }

    }

    private class UpdateSeekbar extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {

            while (playFlag) {
                try {//工作线程里面进行的
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.publishProgress(mediaPlayer.getCurrentPosition());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //主线程，也就是UI线程运行的
            seekBar.setProgress(values[0]);
        }
    }

    private class MySeekbarChangeListner implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }

}
