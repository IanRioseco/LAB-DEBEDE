package com.example.backendTienda.entity;

public class ValoracionProducto {
    private int puntuacion;
    private String comentario;

    public ValoracionProducto() {
    }

    public ValoracionProducto(int puntuacion, String comentario) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
