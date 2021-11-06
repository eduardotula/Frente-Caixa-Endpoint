package com.app.host.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.host.model.Vendas;
@Repository
public interface VendasRepository extends JpaRepository<Vendas, Integer>{



}
