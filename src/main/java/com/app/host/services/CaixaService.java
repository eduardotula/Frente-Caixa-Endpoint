package com.app.host.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.repositories.CaixaRepository;


@Service
public class CaixaService{

	@Autowired
	private CaixaRepository repo;
}
