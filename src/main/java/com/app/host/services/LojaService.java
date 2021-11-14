package com.app.host.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.model.Loja;
import com.app.host.repositories.LojaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repo;
	
	public Loja buscar(Integer id) throws ObjectNotFoundException {
		Optional<Loja> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto %s com id: %d não encontrado!",Loja.class.getName(),id)));
	}
	public Loja buscarByNome(String nome) throws ObjectNotFoundException {
		List<Loja> ob = repo.findByNome(nome);
		if(ob.isEmpty()) throw new ObjectNotFoundException(String.format("Objeto %s com id: %d não encontrado!",Loja.class.getName()));
		return ob.get(0);
	}
	public Loja create(Loja loja) throws IllegalArgumentException{
		return repo.save(loja);
	}
}
