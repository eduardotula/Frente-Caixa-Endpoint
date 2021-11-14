package com.app.host.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.host.model.Loja;
import com.app.host.resources.exception.ResourceExceptionHandler;
import com.app.host.services.LojaService;

import javassist.tools.rmi.ObjectNotFoundException;


@RestController
@RequestMapping(value = "/loja")
public class LojaResource {

	@Autowired
	private LojaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findLoja(@PathVariable Integer id) {

		
		try {
			Loja obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
		} catch (ObjectNotFoundException e) {
			return new ResourceExceptionHandler().objectNotFound(e);
		}
	}
	@PostMapping("/")
	public ResponseEntity<?> createLoja(@RequestBody Loja newLoja){
		
		try {
			Loja obj = service.create(newLoja);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			return new ResourceExceptionHandler().standardError(e);
		}
	}
	
	
}
