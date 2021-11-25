package com.app.host;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@EnableAutoConfiguration
@SpringBootApplication
public class HostApplication implements CommandLineRunner{

	

	
	
	public static void main(String[] args) {
		SpringApplication.run(HostApplication.class, args);

		
	}


	@Override
	public void run(String... args) throws Exception {
	}

}
