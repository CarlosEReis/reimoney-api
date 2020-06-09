package com.carloser7.reimoneyapi.reimoneyapi.repository.lancamento;

import java.util.List;

import com.carloser7.reimoneyapi.reimoneyapi.model.Lancamento;
import com.carloser7.reimoneyapi.reimoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
 
  public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}