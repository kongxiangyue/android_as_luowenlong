package app0.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edInt
                = (EditText) findViewById(R.id.editText);

        final EditText edString
                = (EditText) findViewById(R.id.editText2);

        Button btn = (Button) findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("myaction");

                String strVal
                        = edString.getText().toString();
                int intVal
                        = Integer.parseInt(edInt.getText().toString());

                intent.putExtra("key1", strVal);
                intent.putExtra("key2", intVal);

                startActivity(intent);




            }
        });






    }
}
