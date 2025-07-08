package com.example.backendTienda.DTO;

public class CompraRespuestaDTO implements java.io.Serializable {
    private String detalle;
    private String fecha;
    private Integer total;
    private String mensaje;

    public CompraRespuestaDTO() {}

    public CompraRespuestaDTO(String detalle, String fecha, Integer total) {
        this.detalle = detalle;
        this.fecha = fecha;
        this.total = total;
        this.mensaje = null;
    }

    public CompraRespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

}
