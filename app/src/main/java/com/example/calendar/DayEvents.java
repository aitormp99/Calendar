package com.example.calendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.entidades.Event;
import com.example.calendar.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Date;

public class DayEvents extends AppCompatActivity {

    private RecyclerView recyclerViewEvent;
    ImageButton btnBack;
    private ArrayList<Event> listEvents;
    private ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events);

        btnBack= findViewById(R.id.back);

        savedInstanceState = this.getIntent().getExtras();
        String save_day = savedInstanceState.getString("day");

        Log.i("day"," dia : "+save_day);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
        SQLiteDatabase db = conn.getReadableDatabase();
        Event event = null;
        Cursor cursor = db.rawQuery("select * from "+ Utilidades.TABLA_EVENTOS /*+" where"+Utilidades.CAMPO_HORA+" dia"*/ ,null);

        while (cursor.moveToNext()){
            event = new Event();
            event.setId(cursor.getInt(0));
            event.setTitulo(cursor.getString(1));
            event.setTipo(cursor.getString(2));

            listEvents.add(event);
        }


    }

    }
