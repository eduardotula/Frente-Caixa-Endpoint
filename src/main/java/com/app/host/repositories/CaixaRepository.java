package com.app.host.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.host.model.Caixa;
@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Integer>{

	@Query(value = "SELECT * FROM CAIXA C ORDER BY C.ID DESC LIMIT 1",nativeQuery = true)
	public Optional<Caixa> findLastCaixa();
}
