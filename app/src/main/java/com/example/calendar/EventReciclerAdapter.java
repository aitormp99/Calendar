package com.example.calendar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.entidades.Event;
import com.example.calendar.utilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventReciclerAdapter extends RecyclerView.Adapter<EventReciclerAdapter.EventViewHolder>{


    private ArrayList<Event> listaEvents;

    public EventReciclerAdapter(ArrayList<Event> listaEvents) {

        this.listaEvents = listaEvents;

    }
    public EventViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events,null,false);


        return new EventViewHolder(view);


    }
 
    public void onBindViewHolder(@NonNull EventReciclerAdapter.EventViewHolder holder, int position) {
        holder.titulo.setText(listaEvents.get(position).getTitulo());
        holder.tipo.setText(listaEvents.get(position).getTipo());
        holder.id.setText(listaEvents.get(position).getId().toString());
        holder.descripcion.setText(listaEvents.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {

        return listaEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo, tipo , id , descripcion;
        public ImageButton img;

        public EventViewHolder(@NonNull View itemView) {

            super(itemView);

            titulo =  itemView.findViewById(R.id.textNombre);
            tipo =  itemView.findViewById(R.id.textTipo);
            id = itemView.findViewById(R.id.textId);
            descripcion =  itemView.findViewById(R.id.textDescripcion);
            img = itemView.findViewById(R.id.imageFlecha);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("infor", "se ha clicado");
                    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(v.getContext(),"bd_usuarios",null,1);
                    SQLiteDatabase db = conn.getWritableDatabase();



                    String delete ="delete from "+ Utilidades.TABLA_EVENTOS+" where "+Utilidades.CAMPO_ID+" = "+id.getText();
                    db.execSQL(delete);
                    Toast.makeText(v.getContext(),R.string.NotificacionBorrado,Toast.LENGTH_LONG).show();

                    db.close();





                }
            });

        }

    }
}
