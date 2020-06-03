package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.carloser7.reimoneyapi.reimoneyapi.event.RecursoCriadoEvent;
import com.carloser7.reimoneyapi.reimoneyapi.model.Categoria;
import com.carloser7.reimoneyapi.reimoneyapi.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
  
  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public ResponseEntity<?> listar() {
     List<Categoria> categorias = categoriaRepository.findAll();
    return ResponseEntity.ok(categorias);
  }

  @PostMapping
  public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
    
    Categoria categoriaSalva = categoriaRepository.save(categoria);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Categoria> buscaPeloCodigo(@PathVariable Long codigo)  {
    Optional<Categoria> findById = this.categoriaRepository.findById(codigo);
    return findById.isPresent() ? ResponseEntity.ok(findById.get()) : ResponseEntity.notFound().build();
  }
}