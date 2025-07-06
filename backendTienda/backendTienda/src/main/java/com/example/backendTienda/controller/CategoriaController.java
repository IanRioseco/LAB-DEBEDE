package com.example.backendTienda.controller;


import com.example.backendTienda.entity.Categoria;
import com.example.backendTienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> getallcategorias(){
        return  categoriaService.getallcategorias();
    }

    @PostMapping("/initialize")
    public String inicializarCategorias() {
        categoriaService.inicializarCategorias();
        return "Categorías inicializadas";
    }
}
