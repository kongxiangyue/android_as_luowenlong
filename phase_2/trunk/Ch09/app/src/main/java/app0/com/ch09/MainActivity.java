package app0.com.ch09;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;
    private Button animaBtn;
    private Button cancelBtn;
    private ObjectAnimator translationUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.button);
        animaBtn = (Button) findViewById(R.id.button2);
        cancelBtn = (Button) findViewById(R.id.button3);

        animaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this
                        , "Click AnimaBtn"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translationUP
                        = ObjectAnimator.ofFloat(animaBtn
                        , "Y"
                        , animaBtn.getY()
                        , 0);
                translationUP.setDuration(3000);
                translationUP.start();
                translationUP.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.d("my_android", "cancel");

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        translationUP.cancel();
                    }
                });

            }
        });


    }
}
