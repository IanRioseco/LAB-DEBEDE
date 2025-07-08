
package com.example.backendTienda.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "id_carro", nullable = false)
    private Carro carro;

    @Column(name = "detalle", length = 255)
    private String detalle;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "total")
    private Integer total;

    // Getters y setters
    public Long getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }
    public Carro getCarro() {
        return carro;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
}
