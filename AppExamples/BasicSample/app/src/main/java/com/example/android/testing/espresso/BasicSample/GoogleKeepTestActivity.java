package com.example.android.testing.espresso.BasicSample;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class GoogleKeepTestActivity extends AppCompatActivity {

    private static final String TAG = "GoogleKeepTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "GoogleKeepTestActivity started");

        // List all installed packages and verify Google Keep installation
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        boolean keepInstalled = false;

        for (PackageInfo packageInfo : packages) {
            Log.d(TAG, "Installed package: " + packageInfo.packageName);
            if (packageInfo.packageName.equals("com.google.android.keep")) {
                keepInstalled = true;
                break;
            }
        }

        if (keepInstalled) {
            Log.d(TAG, "Google Keep está instalado.");
            // Código para iniciar o Google Keep diretamente
            Intent intent = pm.getLaunchIntentForPackage("com.google.android.keep");
            if (intent != null) {
                startActivity(intent);
                Log.d(TAG, "Google Keep started");
            }
        } else {
            Log.e(TAG, "Google Keep (Notas do Keep) não está instalado.");
            Toast.makeText(this, "Google Keep (Notas do Keep) não está instalado.", Toast.LENGTH_LONG).show();
        }


        finish();
    }
}






