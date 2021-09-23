package com.example.mappe1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Locale;

public class PrefActivity extends AppCompatActivity {


    private Spinner spinner;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferences_editor;
    private SetLocaleLanguage setLocaleLanguage = new SetLocaleLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getData();
        setContentView(R.layout.activity_preferences);

        //Tilgang til sharedpreferences for å lagre valgt språk og antall spørsmål i spillet
        preferences_editor = getSharedPreferences("Pref", MODE_PRIVATE).edit();

        //Sett språk fra SharedPreferences
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        setLocaleLanguage.setLanguage(preferences, res);
        setContentView(R.layout.activity_preferences);

        //Lager spinner (meny) for valg av språk. Språk lastes fra array.
        spinner = (Spinner) findViewById(R.id.spinnerVelgSpråk);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.språk, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Må settes i post.Runnable for å unngå at listner kjører igjen når siden lastes på nytt ved endring av språk
        spinner.post(new Runnable() {
            public void run() {

                spinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            //Sjekker for valg av spinner og tilbyr bruker å bytte språk. Kan ikke "bytte" til gjeldende språk
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                spinner.setSelection(0);
                                Object item = parent.getItemAtPosition(pos);
                                String currentLanguage = Locale.getDefault().getLanguage();

                                if (item.toString().equals("Norwegisch")) {
                                    if(!currentLanguage.equals("en") || !currentLanguage.equals("no")) {
                                        confirmLanguageDialog("no");
                                    }
                                }

                                if (item.toString().equals("Tysk")) {
                                    if(!currentLanguage.equals("en") || !currentLanguage.equals("de")) {
                                        confirmLanguageDialog("de");
                                    }
                                }

                            }

                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
            }
        });
    }


    //Lager dialog boks, lagrer valgt språk til SharedPref, setter språk og laster siden inn på nytt
    private void confirmLanguageDialog(String lang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtekst);
        builder.setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Lagrer og setter språk hvis bruker bekrefter endring
                preferences_editor.putString("localeLang", lang);
                preferences_editor.apply();
                preferences = getSharedPreferences("Pref", MODE_PRIVATE);
                Resources res = getResources();
                setLocaleLanguage.setLanguage(preferences, res);
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

    //Setter antall spørmål i spillet (lengde på array), lagres i SharedPref.
    public void onClickedAntallSpm(View view) {
        boolean clicked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.velg5spm:
                if (clicked) {
                    preferences_editor.putInt("questionArray_length", 5);
                    preferences_editor.apply();
                    break;
                }
            case R.id.velg10spm:
                if (clicked) {
                    preferences_editor.putInt("questionArray_length", 10);
                    preferences_editor.apply();
                    break;
                }
            case R.id.velg15spm:
                if (clicked) {
                    preferences_editor.putInt("questionArray_length", 15);
                    preferences_editor.apply();
                    break;
                }
        }
    }

}
