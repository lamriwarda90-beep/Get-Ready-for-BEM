package com.getreadyforbem.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    WebResourceRequest request) {

                Uri uri = request.getUrl();

                if ("https".equals(uri.getScheme())
                        && "willowy-caramel-1bd458.netlify.app"
                        .equals(uri.getHost())) {
                    return false;
                }

                if ("http".equals(uri.getScheme())
                        || "https".equals(uri.getScheme())) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW, uri));
                    return true;
                }

                return true;
            }
        });

        webView.loadUrl(
            "https://willowy-caramel-1bd458.netlify.app"
        );
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
