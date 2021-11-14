package com.app.host;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.host.model.Caixa;
import com.app.host.model.Loja;
import com.app.host.model.Vendas;
import com.app.host.services.CaixaService;
import com.app.host.services.LojaService;
import com.app.host.services.VendasService;

@EnableAutoConfiguration
@SpringBootApplication
public class HostApplication implements CommandLineRunner{

	
	@Autowired
	private CaixaService caixServ;
	@Autowired
	private VendasService vendasServ;
	@Autowired
	private LojaService lojaServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HostApplication.class, args);
		

		
	}


	@Override
	public void run(String... args) throws Exception {
	}

}
