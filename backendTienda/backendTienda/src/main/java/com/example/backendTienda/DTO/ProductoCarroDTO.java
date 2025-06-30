package com.example.backendTienda.DTO;

public class ProductoCarroDTO {
    private Integer idProducto;
    private Integer idCarro;
    private Integer cantidadProducto;

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
    public Integer getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
