package com.loja65.outbound.adapters.repositories;

import com.loja65.domain.model.TotalVendasPeriod;
import com.loja65.outbound.adapters.entity.VendaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {

    @Query("SELECT v FROM venda v WHERE v.caixa.loja.lojaId = :lojaId AND v.createdAt BETWEEN :dataInicial AND :dataFinal order by v.createdAt ASC")
    List<VendaEntity> findAllByCreatedAtBetweenAndLojaLojaId(@Param("dataInicial") LocalDateTime dataInicial,
                                                             @Param("dataFinal") LocalDateTime dataFinal,
                                                             @Param("lojaId") Integer lojaId, Pageable pageable);
    @Query("SELECT v FROM venda v WHERE v.caixa.loja.lojaId = :lojaId AND v.localVendaId = :localVendaId")
    List<VendaEntity> findByLojaIdAndlocalVendaId(@Param("lojaId")Integer lojaId, @Param("localVendaId") Integer localVendaId);

    @Query(value = "from venda v WHERE " +
            "(v.createdAt BETWEEN :dataInicial AND :dataFinal) " +
            "AND (:funcionario IS NULL OR UPPER(v.caixa.funcionario) LIKE cast('%'||UPPER(cast(:funcionario as text))||'%' as text)) " +
            "AND (:lojaId IS NULL OR v.caixa.loja.lojaId = :lojaId)")
    Page<VendaEntity> findByFilters(@Param("dataInicial") LocalDateTime dataInicial,
                                      @Param("dataFinal") LocalDateTime dataFinal,
                                      @Param("lojaId") Integer lojaId,
                                      @Param("funcionario") String funcionario,
                                      Pageable pageable);

    @Query(value = "SELECT sum(v.valorCartao), sum(v.valorDinheiro), sum(v.valorTotal) from venda v WHERE " +
            "(v.createdAt BETWEEN :dataInicial AND :dataFinal) " +
            "AND (:funcionario IS NULL OR UPPER(v.caixa.funcionario) LIKE cast('%'||UPPER(cast(:funcionario as text))||'%' as text)) " +
            "AND (:lojaId IS NULL OR v.caixa.loja.lojaId = :lojaId)")
    Object getSumVendasPeriodWithFilters(@Param("dataInicial") LocalDateTime dataInicial,
                                                    @Param("dataFinal") LocalDateTime dataFinal,
                                                    @Param("lojaId") Integer lojaId,
                                                    @Param("funcionario") String funcionario);
}
