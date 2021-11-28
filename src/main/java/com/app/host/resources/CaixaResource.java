package com.app.host.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.host.model.Caixa;
import com.app.host.model.Loja;
import com.app.host.resources.exception.ResourceExceptionHandler;
import com.app.host.services.CaixaService;
import com.app.host.services.LojaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaResource {

	@Autowired
	private CaixaService service;
	@Autowired
	private LojaService serviceLoja;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCaixa(@PathVariable Integer id) {

		try {
			Caixa obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			return new ResourceExceptionHandler().objectNotFound(e);
		}
	}

	@PutMapping("/caixa-status")
	public ResponseEntity<?> updateLastCaixaStatus(@RequestBody Caixa caixaR,@RequestParam String loja) {
		try {
			Caixa caixa = service.buscarUltimoCaixa(loja);
			caixa.setStatus(caixaR.getStatus());
			System.out.println(caixa.getId());
			service.create(caixa);
			return ResponseEntity.status(HttpStatus.CREATED).body(caixa);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			return new ResourceExceptionHandler().objectNotFound(e);
		}catch (Exception e) {
			return new ResourceExceptionHandler().standardError(e);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> createCaixa(@RequestBody Caixa newCaixa,@RequestParam(name = "loja") String lojaS) {
		try {
			Loja loja = serviceLoja.buscarByNome(lojaS);
			newCaixa.setLoja(loja);
			
			Caixa obj = service.create(newCaixa);
			System.out.println(obj.getId());
			return ResponseEntity.ok(obj);
		}catch (ObjectNotFoundException e) {
			return new ResourceExceptionHandler().objectNotFound(e);
		} catch (Exception e) {
			return new ResourceExceptionHandler().standardError(e);
		}
	}
}
