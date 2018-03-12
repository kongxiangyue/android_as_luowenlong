package app0.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] tvID = new int[] { R.id.textView1
                , R.id.textView2
                , R.id.textView3
                , R.id.textView4
                , R.id.textView5
                , R.id.textView6
                , R.id.textView7

        };

        int[] tvColor = new int[] {R.color.color1
                , R.color.color2
                , R.color.color3
                , R.color.color4
                , R.color.color5
                , R.color.color6
                , R.color.color7
        };


        int[] tvText = new int[] { R.string.colorTx1
                , R.string.colorTx2
                , R.string.colorTx3
                , R.string.colorTx4
                , R.string.colorTx5
                , R.string.colorTx6
                , R.string.colorTx7
        };

        for (int i = 0; i < 7; i++) {
            TextView tv = (TextView) findViewById(tvID[i]);
            tv.setText(getResources().getString(tvText[i]));
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(getResources().getColor(tvColor[i]));
            tv.setHeight((int)(getResources().getDimension(R.dimen.baisc))*(i+2)/2);

        }




    }
}
