package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.carloser7.reimoneyapi.reimoneyapi.model.Categoria;
import com.carloser7.reimoneyapi.reimoneyapi.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
  
  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public ResponseEntity<?> listar() {
     List<Categoria> categorias = categoriaRepository.findAll();
    return ResponseEntity.ok(categorias);
  }

  @PostMapping
  public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
    
    Categoria categoriaSalva = categoriaRepository.save(categoria);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path("/{codigo}")
      .buildAndExpand(categoriaSalva.getCodigo())
      .toUri();

    return ResponseEntity.created(uri).body(categoriaSalva);
  }

  @GetMapping("/{codigo}")
  public Categoria buscaPeloCodigo(@PathVariable Long codigo)  {
    return this.categoriaRepository.findById(codigo).orElse(null);
  }
}