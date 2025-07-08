package com.example.backendTienda.DTO;

public class RankingDeseadosDTO {
    private Integer idProducto;
    private String nombreProducto;
    private Long totalDeseado;

    public RankingDeseadosDTO(Integer idProducto, String nombreProducto, Long totalDeseado) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.totalDeseado = totalDeseado;
    }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public Long getTotalDeseado() { return totalDeseado; }
    public void setTotalDeseado(Long totalDeseado) { this.totalDeseado = totalDeseado; }
}
