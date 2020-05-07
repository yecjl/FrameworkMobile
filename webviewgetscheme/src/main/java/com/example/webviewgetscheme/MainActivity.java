package com.example.webviewgetscheme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通过报错ERR_UNKNOWN_URL_SCHEME，获取应用scheme, 比如抖音的是 snssdk1128:// （也可以反编译，或者百度）
        WebView webView = findViewById(R.id.web);
        setWebSettings(webView.getSettings());
        webView.loadUrl("https://v.douyin.com/KWVABo/"); // 抖音分享链接 -- 点击打开抖音，就会报错ERR_UNKNOWN_URL_SCHEME
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                if (url.startsWith("http://") || url.startsWith("https://")) {
                view.loadUrl(url);
//                } else {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
//                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    protected void setWebSettings(WebSettings webSettings) {
        /***********设置为默认值***********/
        //支持缩放，默认为true。
        webSettings.setSupportZoom(true);
        //隐藏原生的缩放控件，默认为false
        webSettings.setDisplayZoomControls(false);
        //webview字体不随系统字体放大，设置页面上的文本缩放百分比，默认100
        webSettings.setTextZoom(100);
        /*******************************/

        webSettings.setJavaScriptEnabled(true);
        //编码方式
        webSettings.setDefaultTextEncodingName("UTF-8");
        //支持通过js打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //把所有内容放大webview等宽的一列中，设置布局，会引起WebView的重新布局（relayout）,默认值NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //打开本地缓存提供JS调用，DOM存储API是否可用，默认false
        webSettings.setDomStorageEnabled(true);
        String appCachePath = this.getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        //应用缓存API是否可用，默认值false, 结合setAppCachePath(String)使用。
        webSettings.setAppCacheEnabled(true);
        //数据库存储API是否可用，默认值false
        webSettings.setDatabaseEnabled(true);
        //不使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

}