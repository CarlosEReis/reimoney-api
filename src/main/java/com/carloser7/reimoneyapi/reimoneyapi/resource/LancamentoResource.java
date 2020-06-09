package com.carloser7.reimoneyapi.reimoneyapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.carloser7.reimoneyapi.reimoneyapi.event.RecursoCriadoEvent;
import com.carloser7.reimoneyapi.reimoneyapi.exceptionhandler.ReimoneyExceptionHandler.Erro;
import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.repository.LancamentoRepository;
import com.carloser7.reimoneyapi.reimoneyapi.repository.filter.LancamentoFilter;
import com.carloser7.reimoneyapi.reimoneyapi.service.LancamentoService;
import com.carloser7.reimoneyapi.reimoneyapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
  private LancamentoService lancamentoService;

  @Autowired
  public ApplicationEventPublisher publisher;

  @Autowired
  private MessageSource messageSource;

  @PostMapping
  public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
    Lancamento lancamentoSalvo = this.lancamentoService.salvar(lancamento);
    publisher.publishEvent(new  RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
  }

  @GetMapping
  public ResponseEntity<?> pesquisa(LancamentoFilter lancamentoFilter) {
    List<Lancamento> lancamentos = this.lancamentoRepository.filtrar(lancamentoFilter);
    return ResponseEntity.ok(lancamentos);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Lancamento> buscaPeloCodigo(@PathVariable Long codigo) {
    Optional<Lancamento> findById = this.lancamentoRepository.findById(codigo);
    return findById.isPresent() ? ResponseEntity.ok(findById.get()) : ResponseEntity.notFound().build(); 
  }

  @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
  public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
    String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
    String mensagemDesenvolvedor = ex.toString();
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
    return ResponseEntity.badRequest().body(erros);
  }

}