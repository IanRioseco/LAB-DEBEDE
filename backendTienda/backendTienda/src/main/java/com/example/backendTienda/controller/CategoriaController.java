package com.example.backendTienda.controller;


import com.example.backendTienda.entity.Categoria;
import com.example.backendTienda.service.CategoriaRepository;
import com.example.backendTienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> getallcategorias(){
        return  categoriaService.getallcategorias();
    }
}
