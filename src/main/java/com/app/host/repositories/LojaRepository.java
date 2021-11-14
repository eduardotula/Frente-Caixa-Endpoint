package com.app.host.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.host.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer>{

	List<Loja> findByNome(String nome);

}
