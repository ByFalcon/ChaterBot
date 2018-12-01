package org.ieszaidinvergeles.dam.chaterbot.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chaterbot.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.TablaConversacion.SQL_CREAR_CONVERSACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +  Contrato.TablaConversacion.TABLA_NOMBRE);
    }
}
