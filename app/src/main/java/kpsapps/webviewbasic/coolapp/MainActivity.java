package kpsapps.webviewbasic.coolapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String kpsurl;
    String homeurl="#";
    String myprofile="#";
    String changepwd="#";
    String logout="#";
    String editmyinfo="#";
    String coursereg="#";
    String finalistexit="#";
    String coursework ="#";
    String courseresult="#";
    String coursematerial="#";
    String graduation;
    String accomodation;


    public SwipeRefreshLayout sr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //progressbar
        ProgressBar progressBar;
        progressBar =(ProgressBar)findViewById(R.id.progress);
        progressBar.setMax(100);

        sr =(SwipeRefreshLayout)findViewById(R.id.sr);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
            //webview codes starts
            WebView htmlWebView = (WebView) findViewById(R.id.webView);
            htmlWebView.setWebViewClient(new CustomWebViewClient());
            WebSettings webSetting = htmlWebView.getSettings();
            htmlWebView.getSettings().setDomStorageEnabled(true);
            htmlWebView.getSettings().setJavaScriptEnabled(true);
            htmlWebView.getSettings().setSupportZoom(true);
            htmlWebView.getSettings().setBuiltInZoomControls(true);

//some codes are removed for security purpose
//WhatsApp +255 657 197 284 for more info

    }
 
}
