package org.ieszaidinvergeles.dam.chaterbot.BaseDatos;

import android.content.ContentValues;

import org.ieszaidinvergeles.dam.chaterbot.POJO.Conversacion;

public class Utilidades {

    public static ContentValues contentValuesConversacion(Conversacion conversacion){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contrato.TablaConversacion.COL_AUTOR, conversacion.getAutor());
        contentValues.put(Contrato.TablaConversacion.COL_MENSAJE, conversacion.getMensaje());
        contentValues.put(Contrato.TablaConversacion.COL_FECHA, conversacion.getFecha().toString());

        return contentValues;
    }

}
