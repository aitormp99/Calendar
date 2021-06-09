package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageButton btnSettings;
    BottomNavigationView bottomNavigation;
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
        setContentView(R.layout.activity_main);

        btnSettings = findViewById(R.id.btnSettings);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nhome);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nlocation:
                        Intent location = new Intent(getApplicationContext(), LocationActivity.class);
                        startActivity(location);
                        Log.i("click", "se ha clicado");
                        return true;
                    case R.id.nhome:

                        return true;
                    case R.id.ncalendar:
                        Intent calendar = new Intent(getApplicationContext(), CalendarActivity.class);
                        startActivity(calendar);
                        Log.i("click", "se ha clicado");
                        return true;
                    default:
                        return false;
                }
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Preferencias.class);
                startActivity(intent);
                finish();
            }
        });
    }
}