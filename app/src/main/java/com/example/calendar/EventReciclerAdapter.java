package com.example.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.entidades.Event;

import java.util.ArrayList;

public class EventReciclerAdapter extends RecyclerView.Adapter<EventReciclerAdapter.EventViewHolder>{


    private ArrayList<Event> listaEvents;

    public EventReciclerAdapter(ArrayList<Event> listaEvents) {

        this.listaEvents = listaEvents;

    }
    public EventViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events,null,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return new EventViewHolder(view);


    }
 
    public void onBindViewHolder(@NonNull EventReciclerAdapter.EventViewHolder holder, int position) {
        holder.titulo.setText(listaEvents.get(position).getTitulo());
        holder.tipo.setText(listaEvents.get(position).getTipo());
    }

    @Override
    public int getItemCount() {

        return listaEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo, tipo;

        public EventViewHolder(@NonNull View itemView) {

            super(itemView);

            titulo =  itemView.findViewById(R.id.textNombre);
            tipo =  itemView.findViewById(R.id.textTipo);



        }
    }
}
