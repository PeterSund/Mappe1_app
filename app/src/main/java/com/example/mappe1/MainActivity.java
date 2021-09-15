package com.example.mappe1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartGame = (Button) findViewById(R.id.btnStartGame);
        Button btnStats = (Button) findViewById(R.id.btnStats);
        Button btnPref = (Button) findViewById(R.id.btnPref);
        btnStartGame.setOnClickListener(this::onClick);
        btnStats.setOnClickListener(this::onClick);
        btnPref.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartGame:
                Intent intent1 = new Intent(this, GameActivity.class);
                startActivity(intent1);
                break;


            case R.id.btnStats:
                Intent intent2 = new Intent(this, StatsActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnPref:
                Intent intent3 = new Intent(this, PrefActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }


    public static void changeLanguage(String landskode, Resources res) {
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf, dm);
    }
}




