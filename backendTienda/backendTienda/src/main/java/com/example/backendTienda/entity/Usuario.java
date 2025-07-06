package com.example.backendTienda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "correo", length = 50, unique = true)
    private String correo;

    @Column(name = "contrasena", length = 100)
    private String contrasena;

    @Column(name = "rol", nullable = false)
    private String rol;

    @Column(name = "ubicacion", length = 20)
    private String ubicacion;

    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;

    @JsonProperty("apellido")
    @Column(name = "apellido_usuario", length = 50, nullable = false)
    private String apellidoUsuario;

    @Column(name = "direccion_usuario", length = 20)
    private String direccion;

    @OneToOne(mappedBy = "usuarioJefe", cascade = CascadeType.ALL)
    @JsonIgnore
    private Tienda tienda;


    // Getters y setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    @JsonProperty("nombre")
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }
    @JsonProperty("apellido")
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
