package com.example.backendTienda.service;

import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.repository.CarroRepository;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> getCarroById(Integer id) {
        return carroRepository.findById(id);
    }

    public Carro saveCarro(Carro carro, String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        carro.setUsuario(usuario);
        return carroRepository.save(carro);
    }

    public Carro saveCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deleteCarro(Integer id) {
        carroRepository.deleteById(id);
    }
}