package com.loja65.frentecaixa.outbound.adapters.repositories;

import com.loja65.frentecaixa.outbound.adapters.entity.CaixaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CaixaRepository extends JpaRepository<CaixaEntity, Integer> {


    CaixaEntity findByLojaLojaIdAndLocalCaixaId(Integer lojaId, Integer localCaixaId);
    CaixaEntity findByCaixaId(Integer caixaId);

    //@Query("select c from Caixa c where c.createdAt ")
    List<CaixaEntity> findAllByCreatedAtBetweenAndLojaLojaId(LocalDateTime dateInicial, LocalDateTime dateFinal, Integer lojaId);

    @Query(value = "from caixa c WHERE " +
            "(c.createdAt BETWEEN :dataInicial AND :dataFinal) " +
            "AND (:funcionario IS NULL OR UPPER(c.funcionario) LIKE cast('%'||UPPER(cast(:funcionario as text))||'%' as text)) " +
            "AND (:lojaId IS NULL OR c.loja.lojaId = :lojaId)")
    Page<CaixaEntity> findByFilters(@Param("dataInicial") LocalDateTime dataInicial,
                                    @Param("dataFinal") LocalDateTime dataFinal,
                                    @Param("lojaId") Integer lojaId,
                                    @Param("funcionario") String funcionario,
                                    Pageable pageable);

    @Query("SELECT c.funcionario FROM caixa c GROUP BY funcionario")
    List<String> listAllFuncionario();
}
