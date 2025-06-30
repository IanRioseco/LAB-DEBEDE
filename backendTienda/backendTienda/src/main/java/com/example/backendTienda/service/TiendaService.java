package com.example.backendTienda.service;

import com.example.backendTienda.DTO.TiendaDTO;
import com.example.backendTienda.entity.Tienda;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.repository.TiendaRepository;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tienda crearTienda(TiendaDTO tiendaDTO) {
        // Validar que exista el usuario jefe
        Usuario usuarioJefe = usuarioRepository.findById(tiendaDTO.getIdJefe())
                .orElseThrow(() -> new IllegalArgumentException("El usuario jefe con id " + tiendaDTO.getIdJefe() + " no existe"));

        // Crear la tienda y asignar los datos
        Tienda tienda = new Tienda();
        tienda.setNombreTienda(tiendaDTO.getNombreTienda());
        tienda.setUsuarioJefe(usuarioJefe);
        tienda.setNombre(usuarioJefe.getNombreUsuario()); // Guardar el nombre del jefe

        // Guardar la tienda en la base de datos
        return tiendaRepository.save(tienda);
    }

    public TiendaDTO obtenerTiendaConJefe(Long idTienda) {
        if (idTienda == null) {
            // Validar explícitamente y detener el flujo con una excepción clara
            throw new IllegalArgumentException("El ID de la tienda no puede ser nulo");
        }

        // Buscar la tienda por ID
        Tienda tienda = tiendaRepository.findById(idTienda)
                .orElseThrow(() -> new IllegalArgumentException("Tienda no encontrada con el ID proporcionado"));

        // Obtener los datos del jefe
        Long idJefe = null;
        String nombreJefe = null;

        if (tienda.getUsuarioJefe() != null) {
            idJefe = tienda.getUsuarioJefe().getId(); // ID del jefe
            nombreJefe = tienda.getUsuarioJefe().getNombreUsuario(); // Nombre del jefe
        }

        // Retornar el DTO con los datos de la tienda y el jefe
        return new TiendaDTO(
                tienda.getIdTienda(),
                tienda.getNombreTienda(),
                idJefe,
                nombreJefe
        );
    }
}