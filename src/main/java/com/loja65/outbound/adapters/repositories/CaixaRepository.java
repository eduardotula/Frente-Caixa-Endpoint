package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.CaixaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CaixaRepository extends JpaRepository<CaixaEntity, Integer> {


    CaixaEntity findFistByLojaLojaIdOrderByCaixaIdDesc(Pageable pageable, Integer lojaId);
    CaixaEntity findByCaixaId(Integer caixaId);

    //@Query("select c from Caixa c where c.createdAt ")
    List<CaixaEntity> findAllByCreatedAtBetweenAndLojaLojaId(LocalDateTime dateInicial, LocalDateTime dateFinal, Integer lojaId);
}
