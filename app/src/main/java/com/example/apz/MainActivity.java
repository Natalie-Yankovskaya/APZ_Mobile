package com.example.apz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apz.Adapters.WashersAdapter;
import com.example.apz.Fragments.AddFragment;
import com.example.apz.Fragments.ProfileFragment;
import com.example.apz.Fragments.WashingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.apz.Fragments.LaundriesFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        selectorFragment = new LaundriesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectorFragment).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.laundriesFragment:
                            selectedFragment = new LaundriesFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.washingsFragment:
                            selectedFragment = new WashingsFragment();
                            break;
                        case R.id.nav_qr:
                            qrScanner();
                            selectedFragment = new ProfileFragment();
                        case R.id.nav_add:
                            selectedFragment = new AddFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };



    public void qrScanner() {

        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setOrientationLocked(false);
        intent.setCaptureActivity(CaptureAct.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Scanning code");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "error in scanning", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents().toString(), Toast.LENGTH_SHORT).show();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }





}