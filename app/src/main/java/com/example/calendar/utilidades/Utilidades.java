package com.example.calendar.utilidades;

public class Utilidades {

    public static final String TABLA_EVENTOS = "eventos";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_HORA = "hora";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CREAR_TABLA_EVENTO="create table " +TABLA_EVENTOS+ "("+CAMPO_ID+" INTEGER primary key autoincrement, "+CAMPO_TITULO+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_FECHA+" TEXT, "+CAMPO_HORA+" TEXT, "+CAMPO_TIPO+" TEXT)";

}
