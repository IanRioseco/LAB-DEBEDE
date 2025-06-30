package com.example.backendTienda.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Producto_Carro")
public class ProductoCarro implements Serializable {
    @EmbeddedId
    private ProductoCarroId id;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @MapsId("idCarro")
    @JoinColumn(name = "id_carro")
    private Carro carro;

    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    // Getters y setters
    public ProductoCarroId getId() {
        return id;
    }
    public void setId(ProductoCarroId id) {
        this.id = id;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Carro getCarro() {
        return carro;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public Integer getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
