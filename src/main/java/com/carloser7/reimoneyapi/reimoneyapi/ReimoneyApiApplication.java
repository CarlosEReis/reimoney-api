package com.carloser7.reimoneyapi.reimoneyapi;

import com.carloser7.reimoneyapi.reimoneyapi.config.property.ReimoneyApiProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ReimoneyApiProperty.class)
public class ReimoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReimoneyApiApplication.class, args);
	}

}
