package app0.com.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private Button     button;
    private ImageView  imageView;

    int[] imgRes = { R.drawable.leaf1
            , R.drawable.leaf2
            , R.drawable.leaf3
    };

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button    = (Button)    findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Bitmap bm = BitmapFactory.decodeResource(this.getResources()
        //        , imgRes[0]);

        try {
            FileInputStream fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory().getPath()
                    +"/Pictures/leaf1.jpg");
            Bitmap bm = BitmapFactory.decodeStream(fileInputStream);
            imageView.setImageBitmap(bm);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 2;
                opt.inDither = false;
                opt.inPreferredConfig = null;

                Bitmap tmpBm = BitmapFactory.decodeResource(MainActivity.this.getResources()
                        , imgRes[(count++)%3]);

                imageView.setImageBitmap(tmpBm);

            }
        });


    }
}
