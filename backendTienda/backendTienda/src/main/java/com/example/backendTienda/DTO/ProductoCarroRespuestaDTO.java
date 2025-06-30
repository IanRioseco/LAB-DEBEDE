package com.example.backendTienda.DTO;

public class ProductoCarroRespuestaDTO {
    private Integer idProducto;
    private Integer idCarro;
    private Integer cantidadProducto;
    private Integer precioUnitario;
    private Integer subtotal;
    private Integer totalCarro;

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public Integer getIdCarro() { return idCarro; }
    public void setIdCarro(Integer idCarro) { this.idCarro = idCarro; }
    public Integer getCantidadProducto() { return cantidadProducto; }
    public void setCantidadProducto(Integer cantidadProducto) { this.cantidadProducto = cantidadProducto; }
    public Integer getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
    public Integer getSubtotal() { return subtotal; }
    public void setSubtotal(Integer subtotal) { this.subtotal = subtotal; }
    public Integer getTotalCarro() { return totalCarro; }
    public void setTotalCarro(Integer totalCarro) { this.totalCarro = totalCarro; }
}
