package com.example.backendTienda.service;

import com.example.backendTienda.entity.Categoria;
import com.example.backendTienda.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getallcategorias(){
        return categoriaRepository.findAll();
    }


}
