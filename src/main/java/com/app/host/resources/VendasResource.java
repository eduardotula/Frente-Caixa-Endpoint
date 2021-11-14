package com.app.host.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.host.model.Vendas;
import com.app.host.resources.exception.ResourceExceptionHandler;
import com.app.host.services.CaixaService;
import com.app.host.services.VendasService;

import javassist.tools.rmi.ObjectNotFoundException;


@RestController
@RequestMapping(value = "/vendas")
public class VendasResource {

	@Autowired
	private VendasService service;
	@Autowired
	private CaixaService serviceCaixa;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findVendas(@PathVariable Integer id) {

		
		try {
			Vendas obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
		} catch (ObjectNotFoundException e) {
			return new ResourceExceptionHandler().objectNotFound(e);
		}
	}
	@PostMapping("/")
	public ResponseEntity<?> createVendasLastCaixa(@RequestBody Vendas newVendas, @RequestParam (name = "loja") String loja){
		
		try {
			Vendas obj = service.create(newVendas);
			obj.setCaixa(serviceCaixa.buscarUltimoCaixa(loja));
			return ResponseEntity.ok(obj);
		}catch (ObjectNotFoundException e) {
			return new ResourceExceptionHandler().objectNotFound(e);
		} catch (Exception e) {
			return new ResourceExceptionHandler().standardError(e);
		}
	}
}
