package com.carloser7.reimoneyapi.reimoneyapi.repository;

import java.util.Optional;

import com.carloser7.reimoneyapi.reimoneyapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  
  public Optional<Usuario> findByEmail(String email);

}