package com.example.android.testing.espresso.BasicSample;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class BatteryDrainTestActivity extends AppCompatActivity {

    private static final String YOUTUBE_VIDEO_ID = "xVWHuJOmaEk"; //updated to something without adds
    public static final long WATCH_DURATION = 2 * 60 * 1000; // 2 minutes in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_drain);

        // Check for WRITE_SETTINGS permission before adjusting brightness and opening YouTube.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestSystemWritePermission();
            } else {
                proceedWithAppFunctionality();
            }
        } else {
            // For older versions, just proceed.
            proceedWithAppFunctionality();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestSystemWritePermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private void proceedWithAppFunctionality() {
        setSystemBrightness(20); //  1 = 0%, 6 = 25%, 20 = 50%, 36 = 75% xiaomi f3 poco
        openYouTubeVideo(YOUTUBE_VIDEO_ID);
        // Schedule task to bring the app back to the foreground after WATCH_DURATION
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Bring the app back to the foreground
                Intent intent = new Intent(BatteryDrainTestActivity.this, BatteryDrainTestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        }, WATCH_DURATION);
    }

    private void setSystemBrightness(int brightnessValue) {
        ContentResolver resolver = getContentResolver();
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightnessValue);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = brightnessValue / 128.0f; // xiaomi poco f3
        getWindow().setAttributes(layoutParams);
    }

    private void openYouTubeVideo(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
        intent.putExtra("force_fullscreen", true);
        intent.putExtra("finish_on_ended", true);
        startActivity(intent);
    }
}