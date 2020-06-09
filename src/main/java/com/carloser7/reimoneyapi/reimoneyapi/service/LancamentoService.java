package com.carloser7.reimoneyapi.reimoneyapi.service;

import java.util.Optional;

import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.model.Pessoa;
import com.carloser7.reimoneyapi.reimoneyapi.repository.LancamentoRepository;
import com.carloser7.reimoneyapi.reimoneyapi.repository.PessoaRepository;
import com.carloser7.reimoneyapi.reimoneyapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private LancamentoRepository lancamentoRepository;

  public Lancamento salvar(Lancamento lancamento) {
    Optional<Pessoa> pessoa = this.pessoaRepository.findById(lancamento.getPessoa().getCodigo());
    if (pessoa.isPresent() || pessoa.get().isInativa()) {
      throw new PessoaInexistenteOuInativaException();
    }
    return this.lancamentoRepository.save(lancamento);
  }

}