package com.app.host.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.host.model.Caixa;
@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Integer>{

}
