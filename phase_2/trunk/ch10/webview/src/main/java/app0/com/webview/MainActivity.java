package app0.com.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private Button btnGoBack, btnGoforward, btnRefresh, btnGoto;
    private EditText edUrl;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        btnGoBack.setOnClickListener(this);
        btnGoforward.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);
        btnGoto.setOnClickListener(this);

    }

    void bindUI() {
        btnGoBack    = (Button) findViewById(R.id.btnGoback);
        btnGoforward = (Button) findViewById(R.id.btnGofoward);
        btnRefresh   = (Button) findViewById(R.id.btnRefresh);
        btnGoto      = (Button) findViewById(R.id.btnGoto);

        edUrl = (EditText) findViewById(R.id.editText);
        webView = (WebView) findViewById(R.id.webView);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGoback   :
                webView.goBack();
                break;

            case R.id.btnGofoward :
                webView.goForward();
                break;

            case R.id.btnRefresh  :
                webView.reload();
                break;

            case R.id.btnGoto     :
                String strUrl = edUrl.getText().toString();
                if (strUrl != null) {
                    webView.loadUrl(strUrl);
                    webView.setWebViewClient(new MyWebViewClient());
                }
                break;
            default: break;
        }

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            webView.goBack();
            return true;
        }
        return false;
    }

}
