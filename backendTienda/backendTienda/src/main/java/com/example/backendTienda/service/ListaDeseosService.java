package com.example.backendTienda.service;

import com.example.backendTienda.entity.ListaDeseos;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.repository.ListaDeseosRepository;
import com.example.backendTienda.repository.ProductoRepository;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ListaDeseosService {
    @Autowired
    private ListaDeseosRepository listaDeseosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public java.util.List<com.example.backendTienda.DTO.RankingDeseadosDTO> rankingProductosMasDeseados() {
        java.util.List<Object[]> resultados = listaDeseosRepository.rankingProductosMasDeseados();
        java.util.List<com.example.backendTienda.DTO.RankingDeseadosDTO> ranking = new java.util.ArrayList<>();
        for (Object[] fila : resultados) {
            Integer idProducto = (Integer) fila[0];
            String nombreProducto = (String) fila[1];
            Long totalDeseado = (Long) fila[2];
            ranking.add(new com.example.backendTienda.DTO.RankingDeseadosDTO(idProducto, nombreProducto, totalDeseado));
        }
        return ranking;
    }

    public ListaDeseos crearListaDeseos(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isEmpty()) throw new RuntimeException("Usuario no encontrado");
        ListaDeseos lista = new ListaDeseos();
        lista.setUsuario(usuarioOpt.get());
        lista.setProductos(new HashSet<>());
        return listaDeseosRepository.save(lista);
    }

    public List<ListaDeseos> obtenerListasPorUsuario(Long idUsuario) {
        return listaDeseosRepository.findByUsuario_Id(idUsuario);
    }

    public Optional<ListaDeseos> obtenerPorId(Long idLista) {
        return listaDeseosRepository.findById(idLista);
    }

    public ListaDeseos agregarProducto(Long idLista, Integer idProducto) {
        ListaDeseos lista = listaDeseosRepository.findById(idLista).orElseThrow();
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        lista.getProductos().add(producto);
        return listaDeseosRepository.save(lista);
    }

    public ListaDeseos quitarProducto(Long idLista, Integer idProducto) {
        ListaDeseos lista = listaDeseosRepository.findById(idLista).orElseThrow();
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        lista.getProductos().remove(producto);
        return listaDeseosRepository.save(lista);
    }

    public void eliminarLista(Long idLista) {
        listaDeseosRepository.deleteById(idLista);
    }
}
