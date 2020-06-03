package com.carloser7.reimoneyapi.reimoneyapi.service;

import com.carloser7.reimoneyapi.reimoneyapi.model.Pessoa;
import com.carloser7.reimoneyapi.reimoneyapi.repository.PessoaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
  
  @Autowired
  private PessoaRepository pessoaRepository;

  public Pessoa atualiza(Long codigo, Pessoa pessoa) {
    Pessoa pessoaSalva = buscaPessoaPeloCodigo(codigo);
    BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
    return this.pessoaRepository.save(pessoaSalva);
  }
  
  public void atualizaPropriedadeAtivo(Long codigo, Boolean ativo) {
    Pessoa pessoa = buscaPessoaPeloCodigo(codigo);
    pessoa.setAtivo(ativo);    
    this.pessoaRepository.save(pessoa);
  }

  private Pessoa buscaPessoaPeloCodigo(Long codigo) {
    Pessoa pessoaSalva = this.pessoaRepository
      .findById(codigo)
      .orElseThrow(() -> new EmptyResultDataAccessException(1));
    return pessoaSalva;
  }

}