package com.example.cholid_ridwan.nativetojs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    WebSettings mWebSettings;
    Button btn;
    EditText editText;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/www/index.html");
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");

        editText = (EditText)findViewById(R.id.inputTxt);
        textView = (TextView)findViewById(R.id.outputTxt);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:changeOutput('" + String.valueOf(editText.getText()) + "')");
            }
        });
    }

    /*
     * JavaScript Interface. Web code can access methods in here
     * (as long as they have the @JavascriptInterface annotation)
     */
    public class WebViewJavaScriptInterface{

        private Context context;

        /*
         * Need a reference to the context in order to sent a post message
         */
        public WebViewJavaScriptInterface(Context context){
            this.context = context;
        }

        /*
         * This method can be called from Android. @JavascriptInterface
         * required after SDK version 17.
         */
        @JavascriptInterface
        public void changeOutput(String string){
            textView.setText(string);
        }
    }

}
