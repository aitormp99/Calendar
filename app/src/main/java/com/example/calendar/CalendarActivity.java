package com.example.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    public ImageButton btnHome ,btnLocation;
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
        setContentView(R.layout.activity_calendar);







        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);



        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Intent dayEvents = new Intent(getApplicationContext(), com.example.calendar.DayEvents.class);
                Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat formattedDate
                        = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormatted
                        = formattedDate.format(
                        clickedDayCalendar.getTime());

                dayEvents.putExtra("day", dateFormatted);
                startActivity(dayEvents);
            }
        });



    }
}