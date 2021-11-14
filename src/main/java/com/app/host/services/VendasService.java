package com.app.host.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.model.Vendas;
import com.app.host.repositories.VendasRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class VendasService {

	@Autowired
	private VendasRepository repo;
	
	public Vendas buscar(Integer id) throws ObjectNotFoundException {
		Optional<Vendas> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto %s com id: %d não encontrado!",Vendas.class.getName(),id)));
	}
	
	public Vendas create(Vendas vendas) throws IllegalArgumentException{
		return repo.save(vendas);
	}
	

}
