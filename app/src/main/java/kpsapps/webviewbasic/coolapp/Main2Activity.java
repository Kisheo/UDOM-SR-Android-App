package kpsapps.webviewbasic.coolapp;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //webview codes starts
        WebView htmlWebView = (WebView) findViewById(R.id.webView);
        // String newUA= "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
        // htmlWebView.getSettings().setUserAgentString(newUA);
        htmlWebView.setWebViewClient(new Main2Activity.CustomWebViewClienti());
        WebSettings webSetting = htmlWebView.getSettings();
       htmlWebView.getSettings().setJavaScriptEnabled(true);
        htmlWebView.getSettings().setSupportZoom(true);
        htmlWebView.getSettings().setBuiltInZoomControls(true);
        htmlWebView.getSettings().setDisplayZoomControls(false);
        htmlWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        htmlWebView.setScrollbarFadingEnabled(false);
        htmlWebView.getSettings().setAppCacheEnabled(true);
        htmlWebView.loadUrl("https://udom.ac.tz/");


        htmlWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "UDOM SR");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    //webview function
    private class CustomWebViewClienti extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Main2Activity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }


}
