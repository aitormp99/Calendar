package com.example.calendar;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.entidades.Event;
import com.example.calendar.utilidades.Utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DayEvents extends AppCompatActivity {

    private RecyclerView recyclerViewEvent;

    private ArrayList<Event> listEvents;
    private ConexionSQLiteHelper conn;

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
        setContentView(R.layout.activity_day_events);



        savedInstanceState = this.getIntent().getExtras();
        String save_day = savedInstanceState.getString("day");

        Log.i("day"," dia : "+save_day);


         conn =  new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        listEvents = new ArrayList<>();

        recyclerViewEvent = (RecyclerView) findViewById(R.id.recyclerEvents);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));
        consultarListaEventos(save_day);

       EventReciclerAdapter adapter = new EventReciclerAdapter(listEvents);
       recyclerViewEvent.setAdapter(adapter);



    }

    private void consultarListaEventos(String dia) {

        Date hoy = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(hoy);
        SQLiteDatabase db = conn.getReadableDatabase();

        Event event = null;

        Cursor cursor = db.rawQuery("select  * from "+ Utilidades.TABLA_EVENTOS +" where "+Utilidades.CAMPO_FECHA+"="+ "'"+dia+"'" ,null);


        while (cursor.moveToNext()){

            event = new Event();
            event.setId(cursor.getInt(0));
            event.setTitulo(cursor.getString(1));
            event.setDescripcion(cursor.getString(2));
            event.setTipo(cursor.getString(6));

            listEvents.add(event);
        }


    }


    }
