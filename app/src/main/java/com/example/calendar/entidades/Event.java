package com.example.calendar.entidades;

import android.content.Intent;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Event implements Serializable {
    private Integer id;
    private String tipo;
    private String date;
    private Time time;
    private String descripcion;
    private String Titulo;
    private String localizacion;

    public Event(Integer  id, String tipo, String date, Time time, String descripcion, String titulo, String localizacion) {
        this.id = id;
        this.tipo = tipo;
        this.date = date;
        this.time = time;
        this.localizacion = localizacion;
        this.descripcion = descripcion;
        Titulo = titulo;
    }

    public Event() {

    }

    public String getTipo() {
        return tipo;
    }


    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public  void setLocalizacion(String localizacion){
        this.localizacion = localizacion;
    }
    public String getLocalizacion() {
        return localizacion;
    }

}
