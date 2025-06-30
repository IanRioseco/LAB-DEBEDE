package com.example.backendTienda.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;


}
