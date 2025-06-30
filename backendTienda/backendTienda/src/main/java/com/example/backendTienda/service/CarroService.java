package com.example.backendTienda.service;

import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> getCarroById(Integer id) {
        return carroRepository.findById(id);
    }

    public Carro saveCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deleteCarro(Integer id) {
        carroRepository.deleteById(id);
    }
}