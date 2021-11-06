package com.app.host.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.repositories.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repo;
}
