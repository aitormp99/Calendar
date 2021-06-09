package com.example.calendar;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String idioma = sharedPreferences.getString("idioma", "es");
        boolean tema = sharedPreferences.getBoolean("tema", false);
        String letra = sharedPreferences.getString("letra", "Mediano");

        Locale nuevaloc = new Locale(idioma);
        Locale.setDefault(nuevaloc);
        Configuration config = new Configuration();
        config.locale = nuevaloc;

        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        if (tema) {
            setTheme(R.style.Claro);
        } else {
            setTheme(R.style.Oscuro);
        }

        if (letra.equals("Mediano")) {
            setTheme(R.style.mediano);
        } else if (letra.equals("Grande")) {
            setTheme(R.style.grande);
        } else if (letra.equals("Pequeño")) {
            setTheme(R.style.pequeño);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }
}