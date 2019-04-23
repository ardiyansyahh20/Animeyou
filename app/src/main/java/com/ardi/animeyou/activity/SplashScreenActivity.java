package com.ardi.animeyou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.ardi.animeyou.R;

public class SplashScreenActivity extends AppCompatActivity {

    private long ms = 0;
    private long splashTime = 5000;
    private boolean splashActive = true;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    while(splashActive && ms < splashTime){
                        if (!paused){
                            ms = ms + 10;
                            sleep(10);
                            progressBar.setProgress((int)ms);

                        }
                    }
                }catch (Exception e){

                }finally {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}
