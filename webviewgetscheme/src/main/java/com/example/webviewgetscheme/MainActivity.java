package com.example.webviewgetscheme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
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
//        webView.loadUrl("https://ad-bussiness-thirdparty.e.kuaishou.com/#/merchant?from=5&code=1486&ticket=99931828");
        webView.loadUrl("https://v.douyin.com/KWVABo/"); // 抖音分享链接 -- 点击打开抖音，就会报错ERR_UNKNOWN_URL_SCHEME
//        webView.loadUrl("https://d.2dfire.com/micropages/live-detail.html?entity_id=00045856&business_id=00045856&hdt=1588838700617&oAuthHandlerCode=MicroMessengerdirect&profession=0&sdt=1588838700584&industry=0&shop_kind=1&shop_name=sweet&l=1&token=7094ffa002ac3b8ba19e0015eb8b2c78&uid=4b9fdef3acf4401caadc7c0a6fc02e04&v=1&nickname=Vicky&qr_code=14&client=1&openAppId=377068829077031622&appVersion=1.0.0&isvFlag=0"); // 抖音分享链接 -- 点击打开抖音，就会报错ERR_UNKNOWN_URL_SCHEME
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("danke", "shouldOverrideUrlLoadingListener: " + url);
//                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
//                } else {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
//                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setDownloadListener(getDownloadListener());
    }

    /**
     * 下载文件监听，跳转浏览器
     */
    protected DownloadListener getDownloadListener() {
        return new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
                if (!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        };
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