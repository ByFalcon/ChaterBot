package org.ieszaidinvergeles.dam.chaterbot.BaseDatos;

import android.provider.BaseColumns;

public class Contrato {

    public Contrato() {
    }

    public static class TablaConversacion implements BaseColumns {


    public static final String TABLA_NOMBRE = "conversacion";

    public static final String COL_AUTOR = "autor";
    public static final String COL_MENSAJE = "mensaje";
    public static final String COL_FECHA = "fecha";

    public static final String SQL_CREAR_CONVERSACION =
            "create table " + TABLA_NOMBRE + " (" +
                    COL_AUTOR + " text NOT NULL, " +
                    COL_MENSAJE + " text not null, " +
                    COL_FECHA + " text not null);";
    }
}
