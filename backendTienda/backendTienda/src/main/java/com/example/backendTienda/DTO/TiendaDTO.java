package com.example.backendTienda.DTO;

public class TiendaDTO {
    private Long idTienda;
    private String nombreTienda;
    private Long idJefe;
    private String nombreJefe;

    // Constructor
    public TiendaDTO(Long idTienda, String nombreTienda, Long idJefe, String nombreJefe) {
        this.idTienda = idTienda;
        this.nombreTienda = nombreTienda;
        this.idJefe = idJefe;
        this.nombreJefe = nombreJefe;
    }

    // Getters y setters
    public Long getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Long idTienda) {
        this.idTienda = idTienda;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public Long getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(Long idJefe) {
        this.idJefe = idJefe;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }
}