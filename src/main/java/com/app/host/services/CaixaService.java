package com.app.host.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.model.Caixa;
import com.app.host.repositories.CaixaRepository;

import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class CaixaService{

	@Autowired
	private CaixaRepository repo;
	
	public Caixa buscar(Integer id) throws ObjectNotFoundException {
		Optional<Caixa> ob = repo.findById(id);
			return ob.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto %s com id: %d não encontrado!",Caixa.class.getName(),id)));
	}
	
	public Caixa buscarUltimoCaixa(String loja)throws ObjectNotFoundException{	
		Optional<Caixa> ca = repo.findLastCaixa(loja);
		return ca.orElseThrow(() -> new ObjectNotFoundException(String.format("Ultimo caixa aberto não encontrado!",Caixa.class.getName())));
	}
	
	public Caixa create(Caixa caixa) throws IllegalArgumentException{
		return repo.save(caixa);
	}
}
