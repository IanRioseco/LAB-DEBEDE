package com.example.backendTienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carro")
    private Integer idCarro;

    @Column(name = "precio_total")
    private Integer precioTotal;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "correo", referencedColumnName = "correo", nullable = false)
    private Usuario usuario;

    // Getters y Setters
    public Integer getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Boolean getEstado() {
        return estado;
    }

}