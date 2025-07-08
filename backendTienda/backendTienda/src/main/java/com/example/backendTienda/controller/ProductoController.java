
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
    // Endpoint para filtrar productos por ubicación geográfica del cliente
    @GetMapping("/filtrar-ubicacion")
    public ResponseEntity<?> filtrarPorUbicacion(@RequestParam(required = false) String ubicacion) {
        if (ubicacion == null || ubicacion.isBlank()) {
            return ResponseEntity.badRequest().body("Debe proporcionar el parámetro 'ubicacion' en la URL");
        }
        List<Producto> productos = productoService.filtrarPorUbicacion(ubicacion);
        return ResponseEntity.ok(productos);
    }
    @Autowired
    private ProductoService productoService;

    // Endpoint para ranking de productos más vendidos
    @GetMapping("/ranking-mas-vendidos")
    public ResponseEntity<List<com.example.backendTienda.DTO.RankingDTO>> getRankingProductosMasVendidos() {
        List<com.example.backendTienda.DTO.RankingDTO> ranking = productoService.rankingProductosMasVendidos();
        return ResponseEntity.ok(ranking);
    }

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
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto, @RequestParam Long usuarioId) {
        java.util.Optional<com.example.backendTienda.entity.Usuario> usuarioOpt = productoService.getUsuarioById(usuarioId);
        if (!usuarioOpt.isPresent() || !("admin".equalsIgnoreCase(usuarioOpt.get().getRol()) || "jefe".equalsIgnoreCase(usuarioOpt.get().getRol()) || "vendedor".equalsIgnoreCase(usuarioOpt.get().getRol()))) {
            return ResponseEntity.status(403).body("No autorizado: solo admin, jefe o vendedor pueden crear productos");
        }
        Producto creado = productoService.saveProducto(producto);
        return ResponseEntity.ok(new ProductoDTO(creado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id, @RequestParam Long usuarioId) {
        java.util.Optional<com.example.backendTienda.entity.Usuario> usuarioOpt = productoService.getUsuarioById(usuarioId);
        if (!usuarioOpt.isPresent() || !("admin".equalsIgnoreCase(usuarioOpt.get().getRol()) || "jefe".equalsIgnoreCase(usuarioOpt.get().getRol()))) {
            return ResponseEntity.status(403).body("No autorizado: solo admin o jefe puede eliminar productos");
        }
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{idProducto}/tiendas/{idTienda}")
    public ResponseEntity<?> asociarProductoATienda(@PathVariable Integer idProducto, @PathVariable Long idTienda, @RequestParam Long usuarioId) {
        java.util.Optional<com.example.backendTienda.entity.Usuario> usuarioOpt = productoService.getUsuarioById(usuarioId);
        if (!usuarioOpt.isPresent() || !("admin".equalsIgnoreCase(usuarioOpt.get().getRol()) || "jefe".equalsIgnoreCase(usuarioOpt.get().getRol()))) {
            return ResponseEntity.status(403).body("No autorizado: solo admin o jefe puede asociar productos a tiendas");
        }
        Producto producto = productoService.asociarProductoATienda(idProducto, idTienda);
        return ResponseEntity.ok(new ProductoDTO(producto));
    }
    // Permitir solo admin o jefe para actualizar productos
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody Producto producto, @RequestParam Long usuarioId) {
        java.util.Optional<com.example.backendTienda.entity.Usuario> usuarioOpt = productoService.getUsuarioById(usuarioId);
        if (!usuarioOpt.isPresent() || !("admin".equalsIgnoreCase(usuarioOpt.get().getRol()) || "jefe".equalsIgnoreCase(usuarioOpt.get().getRol()))) {
            return ResponseEntity.status(403).body("No autorizado: solo admin o jefe puede actualizar productos");
        }
        producto.setIdProducto(id);
        Producto actualizado = productoService.saveProducto(producto);
        return ResponseEntity.ok(new ProductoDTO(actualizado));
    }
    // Método auxiliar para obtener usuario desde ProductoService

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
        public java.util.List<TiendaSimpleDTO> tiendas;
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
                this.tiendas = p.getTiendas().stream().map(TiendaSimpleDTO::new).collect(Collectors.toList());
            }
        }
    }
    // DTO plano para evitar ciclos
    public static class TiendaSimpleDTO {
        public Long idTienda;
        public String nombreTienda;
        public TiendaSimpleDTO(com.example.backendTienda.entity.Tienda t) {
            this.idTienda = t.getIdTienda();
            this.nombreTienda = t.getNombreTienda();
        }
    }
}
