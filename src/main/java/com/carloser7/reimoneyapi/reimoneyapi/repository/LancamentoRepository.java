package com.carloser7.reimoneyapi.reimoneyapi.repository;

import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
  
}