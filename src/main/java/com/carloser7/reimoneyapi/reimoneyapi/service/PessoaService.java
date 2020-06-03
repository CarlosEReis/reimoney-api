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
    Pessoa pessoaSalva = this.pessoaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
    return this.pessoaRepository.save(pessoaSalva);
  }

}