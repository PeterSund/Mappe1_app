package com.s340363_s344093.mappe1;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.util.Locale;

public class SetLocaleLanguage extends AppCompatActivity {

    //Generell metode for å sette språk, lagt i egen klasse da den kalles på mange ganger
    //Henter ut "språkkode" fra shared pref og setter nytt språk
    public void setLanguage(SharedPreferences preferences, Resources res) {
        String lang = preferences.getString("localeLang", "en");
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.setLocale(new Locale(lang));
        res.updateConfiguration(config, dm);
    }
}
