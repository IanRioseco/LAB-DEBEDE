package com.example.backendTienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "JuegoMesa")
public class JuegoMesa extends Producto {
    @Column(name = "tipo", length = 50)
    private String tipo; // rol, guerra, tablero, etc.

    // Getters y setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
