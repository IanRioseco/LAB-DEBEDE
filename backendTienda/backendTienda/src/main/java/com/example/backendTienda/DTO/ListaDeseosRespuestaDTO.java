package com.example.backendTienda.DTO;

import java.util.List;

public class ListaDeseosRespuestaDTO {
    private Long idLista;
    private Long idUsuario;
    private List<ProductoSimpleDTO> productos;

    public Long getIdLista() { return idLista; }
    public void setIdLista(Long idLista) { this.idLista = idLista; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public List<ProductoSimpleDTO> getProductos() { return productos; }
    public void setProductos(List<ProductoSimpleDTO> productos) { this.productos = productos; }

    public static class ProductoSimpleDTO {
        private Integer idProducto;
        private String nombreProducto;
        private Integer precioUnitario;
        public Integer getIdProducto() { return idProducto; }
        public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
        public String getNombreProducto() { return nombreProducto; }
        public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
        public Integer getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
    }
}
