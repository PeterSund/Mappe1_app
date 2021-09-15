package com.example.mappe1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Locale;

public class PrefActivity extends AppCompatActivity {


    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getData();
        setContentView(R.layout.activity_preferences);



        spinner = (Spinner) findViewById(R.id.spinnerVelgSpråk);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.språk, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);


        //Må settes i post.Runnable for å unngå at listner kjører igjen nå siden lastes på nytt ved endring av språk
        spinner.post(new Runnable() {
            public void run() {

                spinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                                Object item = parent.getItemAtPosition(pos);
                                String currentLanguage = Locale.getDefault().getLanguage();
                                System.out.println("POS: " + pos);
                                System.out.println(item.toString());     //prints the text in spinner item.
                                System.out.println(currentLanguage);

                                if (item.toString().equals("Norsk") || item.toString().equals("Norwegisch")) {
                                    if(!currentLanguage.equals("en") || !currentLanguage.equals("no")) {
                                        createAndShowAlertDialog("no");
                                    }
                                }

                                if (item.toString().equals("Tysk") || item.toString().equals("Deutsch")) {
                                    if(!currentLanguage.equals("en") || !currentLanguage.equals("de")) {
                                        createAndShowAlertDialog("de");
                                    }
                                }

                            }

                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
            }
        });
    }


    private void createAndShowAlertDialog(String lang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtekst);
        builder.setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                changeLanguage(lang);
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

    public void changeLanguage(String lang) {
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration(configuration);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    public void onClickedAntallSpm(View view) {
        boolean clicked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.velg5spm:
                if (clicked) {
                    System.out.println("5");
                }
            case R.id.velg10spm:
                if (clicked) {
                    //antall spm = 10
                    System.out.println("10");
                }
            case R.id.velg15spm:
                if (clicked) {
                    //antall spm = 15
                    System.out.println("15");
                }
        }
    }

}
