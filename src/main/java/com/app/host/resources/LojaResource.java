package com.app.host.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.host.model.Vendas;
import com.app.host.resources.exception.ResourceExceptionHandler;
import com.app.host.services.VendasService;

import javassist.tools.rmi.ObjectNotFoundException;


@RestController
@RequestMapping(value = "/vendas")
public class LojaResource {

	@Autowired
	private VendasService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCaixa(@PathVariable Integer id) {

		
		try {
			Vendas obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
		} catch (ObjectNotFoundException e) {
			return new ResourceExceptionHandler().objectNotFound(e);
		}
		

	}
	
	
}
