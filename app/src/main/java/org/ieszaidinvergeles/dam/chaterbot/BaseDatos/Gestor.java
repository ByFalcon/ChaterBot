package org.ieszaidinvergeles.dam.chaterbot.BaseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ieszaidinvergeles.dam.chaterbot.POJO.Conversacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gestor {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestor(Context c) {
        this(c, true);
    }

    public Gestor(Context c, boolean write){
        this.abd = new Ayudante(c);
        if(write){
            bd=abd.getWritableDatabase(); //Error 
        }else {
            bd = abd.getReadableDatabase();
        }
    }

    public void cerrar(){
        abd.close();
    }

    public long insertarConversacion(Conversacion c){
        return bd.insert(Contrato.TablaConversacion.TABLA_NOMBRE, null,
                Utilidades.contentValuesConversacion(c));
    }

    public Cursor getCursorConversaciones(String condicion, String[] argumentos) {
        return bd.query(Contrato.TablaConversacion.TABLA_NOMBRE,
                null,
                condicion,
                argumentos,
                null,
                null,
                Contrato.TablaConversacion.COL_FECHA + " ASC");
    }

    public static Conversacion getConversacion(Cursor c){
        Conversacion conversacion = new Conversacion();
        conversacion.setAutor(c.getString(c.getColumnIndex(Contrato.TablaConversacion.COL_AUTOR)));
        conversacion.setMensaje(c.getString(c.getColumnIndex(Contrato.TablaConversacion.COL_MENSAJE)));
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertido = null;
        try {
            convertido = fechaHora.parse(c.getString(c.getColumnIndex(Contrato.TablaConversacion.COL_FECHA)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        conversacion.setFecha(convertido);

        return conversacion;
    }

    public ArrayList<Conversacion> getConversaciones(String condicion, String[] argumentos){
        ArrayList<Conversacion> conversaciones = new ArrayList<>();
        Cursor cursor = getCursorConversaciones(condicion, argumentos);
        while(cursor.moveToNext()){
            conversaciones.add(getConversacion(cursor));
        }
        cursor.close();
        return conversaciones;
    }

    public static ArrayList<Conversacion> getConversaciones(){
        return getConversaciones();
    }
}
