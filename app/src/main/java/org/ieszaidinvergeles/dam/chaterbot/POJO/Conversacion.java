package org.ieszaidinvergeles.dam.chaterbot.POJO;

import java.util.Date;

public class Conversacion {

    private String autor;
    private String mensaje;
    private Date fecha;

    public Conversacion(String autor, String mensaje, Date fecha) {
        this.autor = autor;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Conversacion() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Conversacion{" +
                "autor='" + autor + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
