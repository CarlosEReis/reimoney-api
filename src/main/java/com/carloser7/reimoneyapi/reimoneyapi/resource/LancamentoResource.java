package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.carloser7.reimoneyapi.reimoneyapi.event.RecursoCriadoEvent;
import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.repository.LancamentoRepository;

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
@RequestMapping("/lancamento")
public class LancamentoResource {
  
  @Autowired
  private LancamentoRepository lancamentoRepository;

  @Autowired
  public ApplicationEventPublisher publisher;

  @PostMapping
  public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
    Lancamento lancamentoSalvo = this.lancamentoRepository.save(lancamento);
    publisher.publishEvent(new  RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
  }

  @GetMapping
  public ResponseEntity<?> listar() {
    List<Lancamento> lancamentos = this.lancamentoRepository.findAll();
    return ResponseEntity.ok(lancamentos);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Lancamento> buscaPeloCodigo(@PathVariable Long codigo) {
    Optional<Lancamento> findById = this.lancamentoRepository.findById(codigo);
    return findById.isPresent() ? ResponseEntity.ok(findById.get()) : ResponseEntity.notFound().build(); 
  }

}