package com.example.mappe1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    SetLocaleLanguage setLocaleLanguage = new SetLocaleLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setter språk fra SharedPreferences
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        setLocaleLanguage.setLanguage(preferences, res);
        setContentView(R.layout.activity_main);

        //Kobler onClick til knapper i menyen
        Button btnStartGame = (Button) findViewById(R.id.btnStartGame);
        Button btnStats = (Button) findViewById(R.id.btnStats);
        Button btnPref = (Button) findViewById(R.id.btnPref);
        btnStartGame.setOnClickListener(this::onClick);
        btnStats.setOnClickListener(this::onClick);
        btnPref.setOnClickListener(this::onClick);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Setter språk fra SharedPreferences når aktivten startes igjen (f. eks navigere tilbake)
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        setLocaleLanguage.setLanguage(preferences, res);
        setContentView(R.layout.activity_main);
    }



    //OnClick for knapper i meny, navigerer til ønsket aktivtet med intent. MainActivity er satt som parent for alle i manifest
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

}




