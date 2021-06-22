
package com.example.calendar.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaParser;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.calendar.ConexionSQLiteHelper;
import com.example.calendar.CreateEvent;
import com.example.calendar.R;
import com.example.calendar.entidades.Event;
import com.example.calendar.utilidades.Utilidades;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar extends Fragment {
    public FloatingActionButton floatingActionButton;
    private ArrayList<Event> listEvents;
    private ConexionSQLiteHelper conn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static Calendar newInstance(String param1, String param2) {
        Calendar fragment = new Calendar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        List<EventDay> eventss = new ArrayList<>();
        listEvents = new ArrayList<>();
        conn =  new ConexionSQLiteHelper(getContext(),"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Event event = null;

        Cursor cursor = db.rawQuery("select  * from " + Utilidades.TABLA_EVENTOS, null);


        while (cursor.moveToNext()) {

            event = new Event();
            event.setId(cursor.getInt(0));
            event.setDate(cursor.getString(3));
            event.setTipo(cursor.getString(6));

            listEvents.add(event);
        }

        if (listEvents.size() == 0) {
        }else {


            for (Event events : listEvents) {


                if (events.getTipo().equals("Cumpleaños")) {
                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    String fecha = events.getDate();
                    Log.i("infor", fecha);

                    String dia = fecha.charAt(0)+"".concat(fecha.charAt(1)+"");


                    String mes = fecha.charAt(3)+"".concat(fecha.charAt(4)+"");

                    String año = fecha.charAt(6) +"".concat( fecha.charAt(7) +"").concat( fecha.charAt(8) +"").concat( fecha.charAt(9) + "");


                    calendar.set(Integer.parseInt(año), Integer.parseInt(mes) - 1, Integer.parseInt(dia));
                    eventss.add(new EventDay(calendar, R.drawable.cumple));



                } else if (events.getTipo().equals("Tarea")) {
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    String fecha = events.getDate();


                    String dia = fecha.charAt(0)+"".concat(fecha.charAt(1)+"");


                    String mes = fecha.charAt(3)+"".concat(fecha.charAt(4)+"");

                    String año = fecha.charAt(6) +"".concat( fecha.charAt(7) +"").concat( fecha.charAt(8) +"").concat( fecha.charAt(9) + "");


                    calendar.set(Integer.parseInt(año), Integer.parseInt(mes) - 1, Integer.parseInt(dia));
                    eventss.add(new EventDay(calendar, R.drawable.examen));


                } else if (events.getTipo().equals("Recordatorio")) {

                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    String fecha = events.getDate();


                    String dia = fecha.charAt(0)+"".concat(fecha.charAt(1)+"");


                    String mes = fecha.charAt(3)+"".concat(fecha.charAt(4)+"");

                    String año = fecha.charAt(6) +"".concat( fecha.charAt(7) +"").concat( fecha.charAt(8) +"").concat( fecha.charAt(9) + "");


                    calendar.set(Integer.parseInt(año), Integer.parseInt(mes) - 1, Integer.parseInt(dia));
                    eventss.add(new EventDay(calendar, R.drawable.recordatorio));


                }
            }
        }



        CalendarView calendarView = view.findViewById(R.id.calendarView);

        calendarView.setEvents(eventss);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newEvent = new Intent(getContext(), CreateEvent.class);
                startActivity(newEvent);
            }
        });


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Intent dayEvents = new Intent(getContext(), com.example.calendar.DayEvents.class);
                java.util.Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat formattedDate
                        = new SimpleDateFormat("dd/MM/yyyy");
                String dateFormatted
                        = formattedDate.format(
                        clickedDayCalendar.getTime());

                dayEvents.putExtra("day", dateFormatted);
                startActivity(dayEvents);
            }
        });
        return view;
    }
}
