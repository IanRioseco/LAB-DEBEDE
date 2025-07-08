package com.example.backendTienda.controller;

import com.example.backendTienda.entity.ValoracionProducto;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.service.ValoracionProductoService;
import com.example.backendTienda.repository.ProductoRepository;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionProductoController {
    @Autowired
    private ValoracionProductoService valoracionProductoService;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> valorarProducto(@RequestBody ValoracionRequest request) {
        Producto producto = productoRepository.findById(request.getIdProducto()).orElse(null);
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario()).orElse(null);
        if (producto == null || usuario == null) {
            return ResponseEntity.badRequest().body("Producto o usuario no encontrado");
        }
        // Solo una valoración por usuario y producto
        if (valoracionProductoService.buscarPorProductoYUsuario(producto, usuario).isPresent()) {
            return ResponseEntity.badRequest().body("Ya has valorado este producto");
        }
        ValoracionProducto valoracion = new ValoracionProducto();
        valoracion.setProducto(producto);
        valoracion.setUsuario(usuario);
        valoracion.setPuntaje(request.getPuntaje());
        valoracion.setComentario(request.getComentario());
        valoracion.setFecha(LocalDateTime.now());
        ValoracionProducto guardada = valoracionProductoService.guardar(valoracion);
        return ResponseEntity.ok(guardada);
    }

    // DTO para recibir el JSON
    public static class ValoracionRequest {
        private Integer idProducto;
        private Long idUsuario;
        private Integer puntaje;
        private String comentario;

        public Integer getIdProducto() { return idProducto; }
        public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
        public Long getIdUsuario() { return idUsuario; }
        public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
        public Integer getPuntaje() { return puntaje; }
        public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
        public String getComentario() { return comentario; }
        public void setComentario(String comentario) { this.comentario = comentario; }
    }

    @GetMapping("/producto/{idProducto}")
    public List<ValoracionProducto> listarPorProducto(@PathVariable Integer idProducto) {
        return valoracionProductoService.listarPorProducto(idProducto);
    }
}
