package com.app.host.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.model.Caixa;
import com.app.host.model.Loja;
import com.app.host.repositories.LojaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repo;
	
	public Loja buscar(Integer id) throws ObjectNotFoundException {
		Optional<Loja> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto %s com id: %d não encontrado!",Caixa.class.getName(),id)));
	}
	
}
