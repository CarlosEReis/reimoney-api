package com.carloser7.reimoneyapi.reimoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("reimoney")
public class ReimoneyApiProperty {
  
  private String originPermidita = "http://localhost:8000";

  private final Seguranca seguranca = new Seguranca();

  public String getOriginPermidita() {
    return originPermidita;
  }

  public void setOriginPermidita(String originPermidita) {
    this.originPermidita = originPermidita;
  }

  public Seguranca getSeguranca() {
    return seguranca;
  }

  public static class Seguranca {

    private boolean enableHttps;

    public boolean isEnableHttps() {
      return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
      this.enableHttps = enableHttps;
    }
    
  }

}