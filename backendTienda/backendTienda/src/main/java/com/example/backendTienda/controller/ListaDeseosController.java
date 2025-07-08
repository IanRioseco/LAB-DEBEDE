package com.example.backendTienda.controller;

import com.example.backendTienda.DTO.ListaDeseosRespuestaDTO;
import com.example.backendTienda.entity.ListaDeseos;
import com.example.backendTienda.service.ListaDeseosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/listas-deseos")
public class ListaDeseosController {
    @Autowired
    private ListaDeseosService listaDeseosService;

    // Endpoint para ranking de productos más deseados
    @GetMapping("/ranking-mas-deseados")
    public ResponseEntity<List<com.example.backendTienda.DTO.RankingDeseadosDTO>> getRankingProductosMasDeseados() {
        List<com.example.backendTienda.DTO.RankingDeseadosDTO> ranking = listaDeseosService.rankingProductosMasDeseados();
        return ResponseEntity.ok(ranking);
    }

    private ListaDeseosRespuestaDTO toDTO(ListaDeseos lista) {
        ListaDeseosRespuestaDTO dto = new ListaDeseosRespuestaDTO();
        dto.setIdLista(lista.getId());
        dto.setIdUsuario(lista.getUsuario().getId());
        List<ListaDeseosRespuestaDTO.ProductoSimpleDTO> productos = lista.getProductos().stream().map(p -> {
            ListaDeseosRespuestaDTO.ProductoSimpleDTO pdto = new ListaDeseosRespuestaDTO.ProductoSimpleDTO();
            pdto.setIdProducto(p.getIdProducto());
            pdto.setNombreProducto(p.getNombreProducto());
            pdto.setPrecioUnitario(p.getPrecioUnitario());
            return pdto;
        }).collect(Collectors.toList());
        dto.setProductos(productos);
        return dto;
    }

    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<ListaDeseosRespuestaDTO> crearLista(@PathVariable Long idUsuario) {
        ListaDeseos lista = listaDeseosService.crearListaDeseos(idUsuario);
        return ResponseEntity.ok(toDTO(lista));
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ListaDeseosRespuestaDTO> obtenerListasPorUsuario(@PathVariable Long idUsuario) {
        return listaDeseosService.obtenerListasPorUsuario(idUsuario).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{idLista}")
    public ResponseEntity<ListaDeseosRespuestaDTO> obtenerPorId(@PathVariable Long idLista) {
        Optional<ListaDeseos> lista = listaDeseosService.obtenerPorId(idLista);
        return lista.map(l -> ResponseEntity.ok(toDTO(l))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{idLista}/producto/{idProducto}")
    public ResponseEntity<ListaDeseosRespuestaDTO> agregarProducto(@PathVariable Long idLista, @PathVariable Integer idProducto) {
        ListaDeseos lista = listaDeseosService.agregarProducto(idLista, idProducto);
        return ResponseEntity.ok(toDTO(lista));
    }

    @DeleteMapping("/{idLista}/producto/{idProducto}")
    public ResponseEntity<ListaDeseosRespuestaDTO> quitarProducto(@PathVariable Long idLista, @PathVariable Integer idProducto) {
        ListaDeseos lista = listaDeseosService.quitarProducto(idLista, idProducto);
        return ResponseEntity.ok(toDTO(lista));
    }

    @DeleteMapping("/{idLista}")
    public ResponseEntity<Void> eliminarLista(@PathVariable Long idLista) {
        listaDeseosService.eliminarLista(idLista);
        return ResponseEntity.noContent().build();
    }
}
