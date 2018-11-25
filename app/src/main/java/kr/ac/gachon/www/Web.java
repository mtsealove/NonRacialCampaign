package kr.ac.gachon.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Web extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.activity_web);

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        String title=intent.getStringExtra("title");

        //제목 설정
        TextView title_t=(TextView)findViewById(R.id.title);
        title_t.setText(title);

        webView=(WebView)findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings=webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);

        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&webView.canGoBack()) {
            webView.goBack();
            return  true;
        }
        return  super.onKeyDown(keyCode, event);
    }
}
