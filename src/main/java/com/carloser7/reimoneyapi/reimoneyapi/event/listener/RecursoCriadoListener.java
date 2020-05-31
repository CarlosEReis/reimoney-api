package com.carloser7.reimoneyapi.reimoneyapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.carloser7.reimoneyapi.reimoneyapi.event.RecursoCriadoEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

  @Override
  public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
    HttpServletResponse response = recursoCriadoEvent.getResponse();
    Long codigo = recursoCriadoEvent.getCodigo();

    URI uri = addHeaderLocation(codigo);

    response.setHeader("Location", uri.toASCIIString());
  }

  private URI addHeaderLocation(Long codigo) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path("/{codigo}")
      .buildAndExpand(codigo)
      .toUri();
    return uri;
  }
  
}