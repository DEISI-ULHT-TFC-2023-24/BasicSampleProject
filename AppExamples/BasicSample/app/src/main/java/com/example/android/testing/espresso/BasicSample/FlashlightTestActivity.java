package com.example.android.testing.espresso.BasicSample;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FlashlightTestActivity extends AppCompatActivity {

    private Handler flashlightHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight_test);

        Button turnOnFlashlightBtn = findViewById(R.id.turnOnFlashlightBtn);

        turnOnFlashlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFlashlight();
            }
        });

    }

    public void turnOnFlashlightForTest() {
        CameraManager cameraManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        }
        try {
            String cameraId = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraId = cameraManager.getCameraIdList()[0];
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void turnOffFlashlightForTest() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false); // Turn off flashlight
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private void toggleFlashlight() {
        turnOnFlashlightForTest();
        flashlightHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                turnOffFlashlightForTest();
            }
        }, 15000);
    }

}
