package com.example.backendTienda.controller;

import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer id) {
        Optional<Producto> productoOpt = productoService.getProductoById(id);
        if (productoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Producto producto = productoOpt.get();
        ProductoDTO dto = new ProductoDTO(producto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public Producto saveProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idProducto}/tiendas/{idTienda}")
    public ResponseEntity<Producto> asociarProductoATienda(@PathVariable Integer idProducto, @PathVariable Long idTienda) {
        Producto producto = productoService.asociarProductoATienda(idProducto, idTienda);
        return ResponseEntity.ok(producto);
    }

    // DTO para la respuesta de producto
    public static class ProductoDTO {
        public Integer idProducto;
        public String nombreProducto;
        public String tipoProducto;
        public Integer precioUnitario;
        public Integer stock;
        public String urlImagen;
        public String direccionComercial;
        public String categoria;
        public java.util.List<TiendaDTO> tiendas;
        public ProductoDTO(Producto p) {
            this.idProducto = p.getIdProducto();
            this.nombreProducto = p.getNombreProducto();
            this.tipoProducto = p.getTipoProducto();
            this.precioUnitario = p.getPrecioUnitario();
            this.stock = p.getStock();
            this.urlImagen = p.getUrlImagen();
            this.direccionComercial = p.getDireccionComercial();
            this.categoria = (p.getCategoria() != null) ? p.getCategoria().getDescripcion() : null;
            if (p.getTiendas() != null) {
                this.tiendas = p.getTiendas().stream().map(TiendaDTO::new).collect(Collectors.toList());
            }
        }
    }
    public static class TiendaDTO {
        public Long idTienda;
        public String nombreTienda;
        public String nombre;
        public TiendaDTO(com.example.backendTienda.entity.Tienda t) {
            this.idTienda = t.getIdTienda();
            this.nombreTienda = t.getNombreTienda();
            this.nombre = t.getNombre();
        }
    }
}
