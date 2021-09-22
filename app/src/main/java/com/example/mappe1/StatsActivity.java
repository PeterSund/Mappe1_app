package com.example.mappe1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class StatsActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getData();

        //Sett språk fra SharedPreferences
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        SetLocaleLanguage setLocaleLanguage = new SetLocaleLanguage();
        setLocaleLanguage.setLanguage(preferences, res);
        setContentView(R.layout.activity_stats);
        loadAndSetStats();
    }

    public void loadAndSetStats() {

        //Setter siste 10 spill i table
        Set<String> scoreSet = preferences.getStringSet("scores", null);
        int [] textViewList = {R.id.statsGameTxt1, R.id.statsGameTxt2, R.id.statsGameTxt3, R.id.statsGameTxt4, R.id.statsGameTxt5};
        if(scoreSet != null) {
            try {
            int indexInViewList = 0;
            for (String s : scoreSet) {
                TextView scoretxt = (TextView) findViewById(textViewList[indexInViewList]);
                scoretxt.setText(s);
                indexInViewList++;
            }
            } catch (Exception e) {
                TextView scoretxt = findViewById(R.id.statsGameTxt1);
                scoretxt.setText(R.string.tableExceptionText);
            }
        }

        //Laster inn og setter totale poeng
        int totalScore = preferences.getInt("totalScore", -1);
        int totalMaxScore = preferences.getInt("totalMaxScore", -1);

        TextView totalScoreTxt = (TextView) findViewById(R.id.totalScore);

        if(totalMaxScore != -1 && totalScore != -1) {
            totalScoreTxt.setText(String.valueOf(totalScore) + " / " + String.valueOf(totalMaxScore));
        } else {
            totalScoreTxt.setText("0/0");
        }

    }

    public void confirmDeleteDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtekst_stats);
        builder.setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                preferences.edit().remove("scores").commit();
                preferences.edit().remove("totalScore").commit();
                preferences.edit().remove("totalMaxScore").commit();
                recreate();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.nei, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


