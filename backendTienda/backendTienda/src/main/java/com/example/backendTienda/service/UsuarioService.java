package com.example.backendTienda.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para guardar un nuevo usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para verificar si un correo ya existe
    public boolean existeCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }

    public void eliminar(Long id) {

    }

    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return null;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}