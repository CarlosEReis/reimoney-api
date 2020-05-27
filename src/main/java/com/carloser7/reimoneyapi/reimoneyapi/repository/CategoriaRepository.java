package com.carloser7.reimoneyapi.reimoneyapi.repository;

import com.carloser7.reimoneyapi.reimoneyapi.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
  
}