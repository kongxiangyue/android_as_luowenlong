package app0.com.urlimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView = null;
    private Bitmap    bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        showPicture("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3467076331,4034849124&fm=11&gp=0.jpg");

    }

    public void showPicture(String url) {

        new AsyncTask<String, Void, String>() {

            @Override  //运行在工作线程
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    InputStream is = url.openStream();
                    bm = BitmapFactory.decodeStream(is);
                    is.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override // 运行在UI（主）线程
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                imageView.setImageBitmap(bm);

            }

        }.execute(url);

    }

}
