package com.example.backendTienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Producto")
@Inheritance(strategy = InheritanceType.JOINED)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre_producto", length = 50, nullable = false)
    private String nombreProducto;

    @Column(name = "tipo_producto", length = 50)
    private String tipoProducto;

    @Column(name = "precio_unitario")
    private Integer precioUnitario;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "url_imagen", length = 200)
    private String urlImagen;

    @Column(name = "direccion_comercial", length = 100)
    private String direccionComercial;

    @ManyToMany
    @JoinTable(
        name = "Tienda_Producto",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "direccion_comercial")
    )
    private java.util.Set<Tienda> tiendas;

    @OneToMany(mappedBy = "producto")
    private java.util.Set<ProductoCarro> productosCarro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // Getters y setters
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUrlImagen() {
        return urlImagen;
    }
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDireccionComercial() {
        return direccionComercial;
    }
    public void setDireccionComercial(String direccionComercial) {
        this.direccionComercial = direccionComercial;
    }

    public java.util.Set<Tienda> getTiendas() {
        return tiendas;
    }
    public void setTiendas(java.util.Set<Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    public java.util.Set<ProductoCarro> getProductosCarro() {
        return productosCarro;
    }
    public void setProductosCarro(java.util.Set<ProductoCarro> productosCarro) {
        this.productosCarro = productosCarro;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
