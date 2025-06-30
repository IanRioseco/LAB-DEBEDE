package com.example.backendTienda.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductoCarroId implements Serializable {
    private Integer idProducto;
    private Integer idCarro;

    // Getters, setters, equals y hashCode
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Integer getIdCarro() {
        return idCarro;
    }
    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoCarroId that = (ProductoCarroId) o;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(idCarro, that.idCarro);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idCarro);
    }
}
