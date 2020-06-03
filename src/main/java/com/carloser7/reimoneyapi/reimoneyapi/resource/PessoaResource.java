package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.carloser7.reimoneyapi.reimoneyapi.event.RecursoCriadoEvent;
import com.carloser7.reimoneyapi.reimoneyapi.model.Pessoa;
import com.carloser7.reimoneyapi.reimoneyapi.repository.PessoaRepository;
import com.carloser7.reimoneyapi.reimoneyapi.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
  
  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public ResponseEntity<?> listar() {
    List<Pessoa> pessoas = this.pessoaRepository.findAll();
    return ResponseEntity.ok(pessoas);
  }

  @PostMapping
  public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
    Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Pessoa> buscaPeloCodigo(@PathVariable Long codigo) {
    Optional<Pessoa> findById = this.pessoaRepository.findById(codigo);
    return findById.isPresent() ? ResponseEntity.ok(findById.get()) : ResponseEntity.notFound().build();
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Pessoa> atualiza(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
    Pessoa pessoaSalva = this.pessoaService.atualiza(codigo, pessoa);
    return ResponseEntity.ok(pessoaSalva);
  }

  @PutMapping("/{codigo}/ativo")
  public void atualizaPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
    this.pessoaService.atualizaPropriedadeAtivo(codigo, ativo);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable Long codigo) {
    this.pessoaRepository.deleteById(codigo);
  }

}