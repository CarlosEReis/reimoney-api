package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.carloser7.reimoneyapi.reimoneyapi.model.Pessoa;
import com.carloser7.reimoneyapi.reimoneyapi.repository.PessoaRepository;

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
@RequestMapping("/pessoa")
public class PessoaResource {
  
  @Autowired
  private PessoaRepository pessoaRepository;

  @GetMapping
  public ResponseEntity<?> listar() {
    final List<Pessoa> pessoas = this.pessoaRepository.findAll();
    return ResponseEntity.ok(pessoas);
  }

  @PostMapping
  public ResponseEntity<Pessoa> criar(@Valid @RequestBody final Pessoa pessoa, final HttpServletResponse response) {
    final Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

    final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path("/{codigo}")
      .buildAndExpand(pessoaSalva.getCodigo())
      .toUri();

    return ResponseEntity.created(uri).body(pessoaSalva);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Pessoa> buscaPeloCodigo(@PathVariable final Long codigo) {
    Optional<Pessoa> findById = this.pessoaRepository.findById(codigo);
    return findById.isPresent() ? ResponseEntity.ok(findById.get()) : ResponseEntity.notFound().build();
  }

}