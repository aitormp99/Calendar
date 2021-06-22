package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.calendar.fragments.Cumpleanos;
import com.example.calendar.fragments.Examen;
import com.example.calendar.fragments.Recordatorio;

import java.util.Locale;

public class CreateEvent extends AppCompatActivity {
    ImageButton cumpleanos, examen, recordatorio;

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
        setContentView(R.layout.activity_create_event);


        Cumpleanos frCumple = new Cumpleanos();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,frCumple).commit();

        cumpleanos = findViewById(R.id.cumple);
        examen= findViewById(R.id.examen);
        recordatorio= findViewById(R.id.recordatorio);

        cumpleanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cumpleanos frCumple = new Cumpleanos();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,frCumple);
                transaction.commit();
            }
        });

        examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Examen frExamen = new Examen();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,frExamen);
                transaction.commit();
            }
        });

        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recordatorio frRecordatorio = new Recordatorio();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor,frRecordatorio);
                transaction.commit();

            }
        });


    }
}