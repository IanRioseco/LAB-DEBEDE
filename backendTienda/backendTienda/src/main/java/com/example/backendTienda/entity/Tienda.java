package com.example.backendTienda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private Long idTienda;

    @Column(name = "nombre_tienda",length = 30)
    private String nombreTienda;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioJefe;

    @ManyToMany(mappedBy = "tiendas")
    @JsonIgnore
    private java.util.Set<Producto> productos;

    //Getters y Setters
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Usuario getUsuarioJefe() {
        return usuarioJefe;
    }
    public void setUsuarioJefe(Usuario usuarioJefe) {
        this.usuarioJefe = usuarioJefe;
    }
    public java.util.Set<Producto> getProductos() {
        return productos;
    }
    public void setProductos(java.util.Set<Producto> productos) {
        this.productos = productos;
    }
}
