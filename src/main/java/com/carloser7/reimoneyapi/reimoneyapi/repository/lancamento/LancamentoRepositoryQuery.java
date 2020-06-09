package com.carloser7.reimoneyapi.reimoneyapi.repository.lancamento;

import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.repository.filter.LancamentoFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {
 
  public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}