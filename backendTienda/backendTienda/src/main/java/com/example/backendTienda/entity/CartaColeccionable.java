package com.example.backendTienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CartaColeccionable")
public class CartaColeccionable extends Producto {
    @Column(name = "rareza", length = 50)
    private String rareza;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "anio")
    private Integer anio;

    // Getters y setters
    public String getRareza() { return rareza; }
    public void setRareza(String rareza) { this.rareza = rareza; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
}
