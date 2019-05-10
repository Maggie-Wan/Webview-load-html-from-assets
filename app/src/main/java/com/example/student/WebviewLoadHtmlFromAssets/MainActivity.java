package com.example.student.WebviewLoadHtmlFromAssets;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv=(WebView)findViewById(R.id.wv);
        //要設定javascript可以運作
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        //android_asset不加s
        wv.loadUrl("file:///android_asset/index.html");
        //加上這個就可以開檔案，因為android 8以後有改strictMode，不能直接用file:///開檔案，要用fileprovider來做
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }
    //當按下手機的back button時的method
    public void onBackPressed(){
        //取得url以後檢查url中有沒有index.html，如果有就跳出activity，沒有就回上一頁
        if(wv.getUrl().contains("index.html")){
            //呼叫父類別的onBackPressed()會直接離開activity，但是不可以將此功能註解掉
            super.onBackPressed();
        }else{
            //回上一頁
            wv.goBack();
        }
    }
}
