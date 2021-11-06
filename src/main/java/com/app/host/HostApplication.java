package com.app.host;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.host.model.Caixa;
import com.app.host.model.Loja;

@EnableAutoConfiguration
@SpringBootApplication
public class HostApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostApplication.class, args);
		
		Caixa caixa = new Caixa(null, "Func", LocalDateTime.now(), new Loja(null, "loja"));
	}

}
