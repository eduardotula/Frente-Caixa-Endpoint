package com.app.host.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.host.repositories.VendasRepository;

@Service
public class VendasService {

	@Autowired
	private VendasRepository repo;
}
