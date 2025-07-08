package com.example.backendTienda.service;

import com.example.backendTienda.entity.JuegoMesa;
import com.example.backendTienda.repository.JuegoMesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoMesaService {
    @Autowired
    private JuegoMesaRepository juegoMesaRepository;

    public JuegoMesa guardar(JuegoMesa juego) {
        return juegoMesaRepository.save(juego);
    }

    public List<JuegoMesa> listarTodos() {
        return juegoMesaRepository.findAll();
    }
}
