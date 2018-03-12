package app0.com.tweenanima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnAlpha;
    private Button btnRotate;
    private Button btnScale;
    private Button btnTranslate;
    private Button btnAll;
    private ImageView imageView;
    private Animation mAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();

        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this
                        , R.anim.alpha_anima);
                imageView.startAnimation(mAnimation);

            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this
                        , R.anim.rotate_anima);
                imageView.startAnimation(mAnimation);

            }
        });

        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this
                        , R.anim.scale_anima);
                imageView.startAnimation(mAnimation);

            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this
                        , R.anim.my_all);
                imageView.startAnimation(mAnimation);

            }
        });

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this
                        , R.anim.translate_anima);
                mAnimation.setInterpolator(MainActivity.this
                        , android.R.anim.accelerate_interpolator);

                imageView.startAnimation(mAnimation);

            }
        });

    }

    private void bindUI() {

        btnAlpha = (Button) findViewById(R.id.button);
        btnRotate = (Button) findViewById(R.id.button2);
        btnScale = (Button) findViewById(R.id.button3);
        btnTranslate = (Button) findViewById(R.id.button4);
        btnAll = (Button) findViewById(R.id.button5);
        imageView = (ImageView) findViewById(R.id.imageView);

    }
}
