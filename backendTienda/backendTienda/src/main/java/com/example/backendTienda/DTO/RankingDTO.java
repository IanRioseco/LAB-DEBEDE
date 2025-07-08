package com.example.backendTienda.DTO;

public class RankingDTO {
    private Integer idProducto;
    private String nombreProducto;
    private Long totalVendido;

    public RankingDTO(Integer idProducto, String nombreProducto, Long totalVendido) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.totalVendido = totalVendido;
    }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public Long getTotalVendido() { return totalVendido; }
    public void setTotalVendido(Long totalVendido) { this.totalVendido = totalVendido; }
}
