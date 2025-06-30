package com.example.backendTienda.controller;

import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // Endpoint principal del recurso
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear un nuevo usuario
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            // Inspeccionar los datos
            System.out.println("Recibido usuario: " + usuario);

            if (usuarioService.existeCorreo(usuario.getCorreo())) {
                return new ResponseEntity<>("El correo ya se encuentra registrado", HttpStatus.CONFLICT);
            }
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)) // En caso de encontrarse
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // En caso de no encontrarse
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Establecer el ID recibido como parámetro
        try {
            Usuario usuarioActualizado = usuarioService.actualizar(usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id); // Eliminar usuario por ID
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}