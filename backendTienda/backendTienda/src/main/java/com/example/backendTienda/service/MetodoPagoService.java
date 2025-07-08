package com.example.backendTienda.service;

import com.example.backendTienda.entity.MetodoPago;
import com.example.backendTienda.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public MetodoPago crearMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public List<MetodoPago> listarMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> obtenerPorId(Long id) {
        return metodoPagoRepository.findById(id);
    }

    public void eliminarMetodoPago(Long id) {
        metodoPagoRepository.deleteById(id);
    }
}
