package com.carloser7.reimoneyapi.reimoneyapi.repository;

import com.carloser7.reimoneyapi.reimoneyapi.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
  
}