package com.example.mappe1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }

    public void loadAndSetStats() {
        //last inn fra shared pref eller array i xml
        //sett i table
    }

    public void deleteAllStatistics() {
        //slett alle statistic og load inn på nytt (kall loadAndSet())
    }

    public void confirmDeleteDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtekst_stats);
        builder.setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("SLETT ALT!!!!");
                //preferences = getSharedPreferences("Pref", MODE_PRIVATE);
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
