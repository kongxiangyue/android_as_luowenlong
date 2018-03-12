package com.example.webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText edPhone;
    private Button   btnSearch;
    private TextView txResult;

    static private String SERV_URL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
    static private String PACE     = "http://WebXml.com.cn/";
    static private String W_NAME   = "getMobileCodeInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhone   = (EditText)findViewById(R.id.editText);
        btnSearch = (Button)findViewById(R.id.button);
        txResult  = (TextView)findViewById(R.id.textView);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strPhone = edPhone.getText().toString();
                if (strPhone != null) {
                    getPhoneInfo(strPhone);
                }

            }
        });

    }

    private void getPhoneInfo(String strPhone) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                final HttpTransportSE httpTransportSE
                        = new HttpTransportSE(SERV_URL);

                httpTransportSE.debug = true;

                SoapObject soapObject = new SoapObject(PACE, W_NAME);
                soapObject.addProperty("mobileCode", params[0]);
                soapObject.addProperty("userID", "");

                final SoapSerializationEnvelope serializa
                        = new SoapSerializationEnvelope(SoapEnvelope.VER10);

                serializa.setOutputSoapObject(soapObject);
                serializa.dotNet = true;

                String local = "";
                try {
                    httpTransportSE.call(PACE + W_NAME, serializa);
                    if (serializa.getResponse() != null) {
                        SoapObject result = (SoapObject) serializa.bodyIn;
                        local = result.getProperty("getMobileCodeInfoResult").toString();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }


                return local;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                txResult.setText(result);
            }
        }.execute(strPhone);

    }
}
