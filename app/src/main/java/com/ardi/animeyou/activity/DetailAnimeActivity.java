package com.ardi.animeyou.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.animeyou.R;
import com.ardi.animeyou.entity.Anime;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAnimeActivity extends AppCompatActivity {

    @BindView(R.id.dtJudul)
    TextView judul;
    @BindView(R.id.dtGambar)
    ImageView gambar;
    @BindView(R.id.dtTanggal)
    TextView tanggal;
    @BindView(R.id.fb)
    TextView fb;
    @BindView(R.id.ig)
    TextView ig;
    @BindView(R.id.video)
    WebView Video;

    public Anime Detail;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);

        ButterKnife.bind(this);
        Detail = getIntent().getParcelableExtra("anime");
        judul.setText(Detail.getJudul());
        tanggal.setText(Detail.getDate());
        Glide.with(this)
                .load(Detail.getImg())
                .into(gambar);

        Video.setWebChromeClient(new MyChrome());
        // Call private class InsideWebViewClient
        Video.setWebViewClient(new Browser_home());
        Video.loadUrl("file:///android_asset/video.html");

        WebSettings video = Video.getSettings();
        video.setJavaScriptEnabled(true);
        //      untuk set webView saat diload pertamakali
        Video.setWebViewClient(new WebViewClient(){

            // method loadPageFinish untuk set semua asset yang ada sebelum selesai di tampilkan
            @Override
            public void onPageFinished(WebView view, String url) {

                String Google = Detail.getVideo();//Masukan string video 1 disini
                Video.loadUrl("javascript:(function(){" +
                        "document.getElementById('Google').src ='"+ Google+"'})()");
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.facebook.com/animeyou.net/");


                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);


            }
        });

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.instagram.com/animeyou_net/");


                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
            }
        });
    }

    class Browser_home extends WebViewClient {

        Browser_home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            setTitle(view.getTitle());
            super.onPageFinished(view, url);

        }
    }

    private class MyChrome extends WebChromeClient{
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }
        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        }
    }
}
