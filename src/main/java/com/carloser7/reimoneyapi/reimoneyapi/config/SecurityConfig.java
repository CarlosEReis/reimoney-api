package com.carloser7.reimoneyapi.reimoneyapi.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("admin").password("{noop}admin").roles("ROLE");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/categorias").permitAll()
        .anyRequest().authenticated()
      .and()
        .httpBasic()
      .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .csrf().disable();

  }
}