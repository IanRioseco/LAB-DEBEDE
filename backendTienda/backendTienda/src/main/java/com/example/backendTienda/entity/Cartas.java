package com.example.backendTienda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cartas")
public class Cartas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carta")
    private Long idCarta;

    @Column(name = "nombre_carta", length = 50, nullable = false)
    private String nombreCarta;

    @Column(name = "tipo_carta", length = 50)
    private String tipoCarta;
}
