package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.List;

import com.carloser7.reimoneyapi.reimoneyapi.model.Categoria;
import com.carloser7.reimoneyapi.reimoneyapi.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
  
  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<Categoria> listar() {
    return categoriaRepository.findAll();
  }

}