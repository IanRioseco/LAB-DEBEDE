package com.example.backendTienda.service;

import com.example.backendTienda.entity.Categoria;
import com.example.backendTienda.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getallcategorias(){
        return categoriaRepository.findAll();
    }

    public void inicializarCategorias(){
        List<String> categorias = Arrays.asList(
            "Guerra",
            "Cooperativo",
            "Competitivo",
            "Familiar",
            "Investigacion",
            "Equipo",
            "coop-Estrategia",
            "Historyia",
            "Estrategia"
        );
        for(String nombre : categorias){
            if(categoriaRepository.findAll().stream().noneMatch(c -> c.getDescripcion().equalsIgnoreCase(nombre))) {
                Categoria cat = new Categoria();
                cat.setDescripcion(nombre);
                categoriaRepository.save(cat);
            }
        }
    }
}
