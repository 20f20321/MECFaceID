package com.example.graduationproject;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash1 extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(() -> {

            if (!isConnectedToCollegeWifi()) {
                Toast.makeText(splash1.this, "Please connect to the college Wi-Fi to proceed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(splash1.this, login.class));
                finish();
                return;
            }

            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {

                startActivity(new Intent(splash1.this, splash2.class));
            } else {

                startActivity(new Intent(splash1.this, login.class));
            }
            finish();
        }, SPLASH_DURATION);
    }


    private boolean isConnectedToCollegeWifi() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            return ssid != null && ssid.equals("\"eduroam_WIFI\"");
        }
        return false;
    }
}
