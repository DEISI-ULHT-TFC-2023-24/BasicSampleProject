package com.example.android.testing.espresso.BasicSample;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class BatteryDrainTestActivity extends AppCompatActivity {


    private static final String YOUTUBE_VIDEO_ID = "dQw4w9WgXcQ";
    public static final long WATCH_DURATION = 2 * 60 * 1000; // 2 minutes in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_drain);


        openYouTubeVideo(YOUTUBE_VIDEO_ID);

        // fecha o youtube depois do watch_duration
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                // fecha a app do youtube
                finishAndRemoveTask();
            }
        }, WATCH_DURATION);
    }

    private void openYouTubeVideo(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
        intent.putExtra("force_fullscreen", true);
        intent.putExtra("finish_on_ended", true);
        startActivity(intent);
    }
}