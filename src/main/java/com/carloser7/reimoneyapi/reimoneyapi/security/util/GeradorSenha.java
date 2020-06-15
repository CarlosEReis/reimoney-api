package com.carloser7.reimoneyapi.reimoneyapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
  
  public static void main(String[] args) {
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("@ngul@r0"));
  }

}