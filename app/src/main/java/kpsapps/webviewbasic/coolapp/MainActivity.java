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
    String homeurl="http://uptymes.com/apps/udom-sr/index.php?v=1";
    String myprofile="https://sr.udom.ac.tz/index.php/private_area/my_profile";
    String changepwd="https://sr.udom.ac.tz/index.php/private_area/student_change_password";
    String logout="https://sr.udom.ac.tz/index.php/authentication/logout";
    String editmyinfo="https://sr.udom.ac.tz/index.php/private_area/edit_my_info";
    String coursereg="https://sr.udom.ac.tz/index.php/private_area/course_registration";
    String finalistexit="https://sr.udom.ac.tz/index.php/private_area/student_exit_survey";
    String coursework ="https://sr.udom.ac.tz/index.php/private_area/view_coursework";
    String courseresult="https://sr.udom.ac.tz/index.php/private_area/view_result";
    String coursematerial="https://sr.udom.ac.tz/index.php/private_area/course_material";
    String graduation="https://sr.udom.ac.tz/index.php/private_area/graduation_confirmation";
    String accomodation="https://sr.udom.ac.tz/index.php/private_area/accommodation";


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
            htmlWebView.getSettings().setDisplayZoomControls(false);
            //  htmlWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            htmlWebView.setScrollbarFadingEnabled(false);
            htmlWebView.getSettings().setAppCacheEnabled(true);

        htmlWebView.getSettings().setAllowFileAccess(true);
        htmlWebView.getSettings().setAppCachePath("/data/data" + getPackageName() + "/cache");
        htmlWebView.getSettings().setSaveFormData(true);
        htmlWebView.getSettings().setDatabaseEnabled(true);
        htmlWebView.getSettings().setDomStorageEnabled(true);
        htmlWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        htmlWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        htmlWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        CookieManager.getInstance().acceptCookie();
        htmlWebView.loadUrl(homeurl);



    }



    @Override
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.webView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(webView.canGoBack()){
            webView.goBack();
        }  else {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;

        } else if (id==R.id.evaluation){

            WebView htmlWebView = (WebView) findViewById(R.id.webView);
            htmlWebView.loadUrl("http://uptymes.com/apps/udom-sr/ems/index.html");
            setTitle("UDOM Evaluation");

            return true;

        } else if (id==R.id.election){

            WebView htmlWebView = (WebView) findViewById(R.id.webView);
            htmlWebView.loadUrl("http://uptymes.com/apps/udom-sr/election.html");
setTitle("UDOSO Election");
            return true;

        } else if (id==R.id.news){

            Intent intent = getPackageManager().getLaunchIntentForPackage("sr.udom.app");
            if (intent != null) {
                // We found the activity now start the activity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                // Bring user to the market or let them choose an app?
                WebView webView=(WebView)findViewById(R.id.webView);
                webView.loadUrl("http://uptymes.com/apps/udom-sr/news/index.html");
                /*
                intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("http://uptymes.com/apps/udom-sr/news/index.html"));
                startActivity(intent); */
            }

            return true;

        } else if (id==R.id.rate){

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("market://details?id="+getPackageName()));
            startActivity(i);

            return true;

        } else if (id==R.id.action_settings){

super.onBackPressed();

            return true;
        } else if (id==R.id.reload){
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.reload();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {

        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = manager.getActiveNetworkInfo();
        boolean hasConnect = (i!= null && i.isConnected() && i.isAvailable());

        if(hasConnect)
        {
            // show the webview
        }
        else
        {
            // do what ever you need when when no internet connection
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new CustomWebViewClient());
            //todo change this if not working well
           // webView.loadUrl("file:///android_asset/no.html");
            webView.loadUrl(homeurl);
        }
        super.onPostResume();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.edit_info) {
            setTitle("Edit My Info");

                WebView webView=(WebView)findViewById(R.id.webView);
                webView.setWebViewClient(new CustomWebViewClient());
                webView.loadUrl(editmyinfo);





        } else if (id == R.id.nav_sr) {
            setTitle("UDOM SR");
WebView webView=(WebView)findViewById(R.id.webView);
webView.setWebViewClient(new CustomWebViewClient());
webView.loadUrl(homeurl);
        } else if (id == R.id.course_reg) {
            setTitle("Course Registration");
                WebView webView=(WebView)findViewById(R.id.webView);
                webView.setWebViewClient(new CustomWebViewClient());
                webView.loadUrl(coursereg);



        } else if (id == R.id.nav_rate) {
            setTitle("UDOM SR");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("market://details?id="+getPackageName()));
            startActivity(i);


        } else if (id == R.id.nav_share) {
            setTitle("UDOM SR");

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "DOWNLOAD UDOM SR App from Google Play "+"\n"+ "http://play.google.com/store/apps/details?id="+getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id==R.id.finalist_exit){
            setTitle("Finalist Exit Survey");

                WebView webView=(WebView)findViewById(R.id.webView);
                webView.setWebViewClient(new CustomWebViewClient());
                webView.loadUrl(finalistexit);


        } else if (id==R.id.courseworks){
                setTitle("Courseworks");
                WebView webView=(WebView)findViewById(R.id.webView);
                webView.setWebViewClient(new CustomWebViewClient());
                webView.loadUrl(coursework);

        }   else if (id == R.id.courseresults) {
            setTitle("Course Results");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(courseresult);

        }   else if (id == R.id.coursematerials) {
            setTitle("Course Materials");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(coursematerial);

        }   else if (id == R.id.graduationconf) {
            setTitle("Graduation Confirmation");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(graduation);

        }   else if (id == R.id.accomodation) {
            setTitle("Accommodation");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(accomodation);

        }   else if (id == R.id.myprofile) {
            setTitle("My Profile");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(myprofile);

        }   else if (id == R.id.changepwd) {
            setTitle("Change Password");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(changepwd);

        }   else if (id == R.id.logout) {
            setTitle("UDOM SR");
            WebView webView=(WebView)findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(logout);
        }    else if (id == R.id.webversion) {

            //todo create link to other activity full webview.
            Toast.makeText(getApplicationContext(), "This feature is not available now.",
                    Toast.LENGTH_LONG).show();
        }   else if (id==R.id.nav_developer) {
            setTitle("UDOM SR");

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("mailto:udom.innovations@gmail.com"));
            startActivity(i);
        }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //webview function
private class CustomWebViewClient extends WebViewClient{

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.endsWith(".pdf")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
       else if (url.endsWith(".docx")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
       else if (url.endsWith(".doc")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
       else if (url.endsWith(".ppt")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
       else if (url.endsWith(".pptx")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
        else if (url.endsWith(".xls")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
       else if (url.endsWith(".xlsx")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file

            return true;
        }
        view.loadUrl(url);
        return true;
    }



        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            final WebView webView = (WebView) findViewById(R.id.webView);
            webView.setWebViewClient(new CustomWebViewClient());
          //  webView.getSettings().setJavaScriptEnabled(true);
          //  webView.loadUrl("file:///android_asset/error.html");


            webView.loadUrl("file:///android_asset/error.html");


        }
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            sr.setRefreshing(true);

        }
        public void onPageFinished(WebView view, String url){
            sr.setRefreshing(false);
            thesty();
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            //  thesty(); was originally called below onpagefinished only.
            thesty();

            super.onLoadResource(view, url);
        }


    }



    private void thesty() {
        try {
            InputStream inputStream = getAssets().open("style2.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            WebView webView=(WebView)findViewById(R.id.webView);

            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();



        }
    }
}
