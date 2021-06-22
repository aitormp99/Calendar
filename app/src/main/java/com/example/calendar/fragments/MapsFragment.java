package com.example.calendar.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calendar.ConexionSQLiteHelper;
import com.example.calendar.R;
import com.example.calendar.entidades.Event;
import com.example.calendar.utilidades.Utilidades;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsFragment extends Fragment {

    private ArrayList<Event> listEvents;
    private ConexionSQLiteHelper conn;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            Geocoder geo = new Geocoder(getContext());
            int maxResultados = 1;
            List<Address> adress = null;
            listEvents = new ArrayList<>();
            conn =  new ConexionSQLiteHelper(getContext(),"bd_usuarios",null,1);
            SQLiteDatabase db = conn.getReadableDatabase();
            Event event = null;
            Cursor cursor = db.rawQuery("select * from "+ Utilidades.TABLA_EVENTOS ,null);

            while (cursor.moveToNext()){

                event = new Event();
                event.setId(cursor.getInt(0));
                event.setTitulo(cursor.getString(1));
                event.setTipo(cursor.getString(6));
                event.setLocalizacion(cursor.getString(5));

                listEvents.add(event);
            }

            if (listEvents.size() == 0){

                LatLng bilbao = new LatLng(-33.852, 151.211);

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(bilbao));

            }else {

                Log.i("infor", "" + listEvents.size());

                for (Event events : listEvents) {
                    try {


                        if(events.getTipo().equals("Cumpleaños")) {

                            adress = geo.getFromLocationName(events.getLocalizacion(), maxResultados);
                            LatLng latLng = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latLng).title(events.getTitulo()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                        }else if(events.getTipo().equals("Tarea")){


                            adress = geo.getFromLocationName(events.getLocalizacion(), maxResultados);
                            LatLng latLng = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latLng).title(events.getTitulo()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                        }else if (events.getTipo().equals("Recordatorio")){


                            adress = geo.getFromLocationName(events.getLocalizacion(), maxResultados);
                            LatLng latLng = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latLng).title(events.getTitulo()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                        }
                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        conn =  new ConexionSQLiteHelper(getContext(),"bd_usuarios",null,1);

    }


}