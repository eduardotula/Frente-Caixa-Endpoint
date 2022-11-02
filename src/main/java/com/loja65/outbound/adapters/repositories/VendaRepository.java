package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.VendaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

    @Query("SELECT v FROM venda v WHERE v.caixa.loja.lojaId = :lojaId AND v.createdAt BETWEEN :dataInicial AND :dataFinal")
    List<VendaEntity> findAllByCreatedAtBetweenAndLojaLojaId(@Param("dataInicial") LocalDateTime dataInicial,
                                                             @Param("dataFinal") LocalDateTime dataFinal,
                                                             @Param("lojaId") Integer lojaId, Pageable pageable);
}
