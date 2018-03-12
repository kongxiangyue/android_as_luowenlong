package app0.com.mycamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnPreView;
    private Button btnTakePhoto;
    private SurfaceView surfaceView;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPreView = (Button) findViewById(R.id.button);
        btnTakePhoto = (Button) findViewById(R.id.button2);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);


        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        btnPreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = Camera.open();

                try {
                    camera.setPreviewDisplay(surfaceHolder);

                    Camera.Parameters parameters = camera.getParameters();

                    parameters.setPictureSize(640, 480);
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    parameters.set("jpeg-qulity", 80);

                    camera.setParameters(parameters);

                    camera.startPreview();
                    camera.autoFocus(null);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        final Camera.PictureCallback savePhoto = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                camera.stopPreview();

                File file = new File(Environment.getExternalStorageDirectory()
                        + "/pictures/gupt.jpg");

                try {
                    file.createNewFile();
                    FileOutputStream fileOS = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 100 , fileOS);
                    fileOS.flush();
                    fileOS.close();

                    camera.stopPreview();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    camera.takePicture(null, null, savePhoto);
                }

            }
        });




    }
}
