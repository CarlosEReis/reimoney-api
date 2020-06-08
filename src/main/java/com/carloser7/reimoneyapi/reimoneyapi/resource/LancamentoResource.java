package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.List;
import java.util.Optional;

import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.repository.LancamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
  
  @Autowired
  private LancamentoRepository lancamentoRepository;

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