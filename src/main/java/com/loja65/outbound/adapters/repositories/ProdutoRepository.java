package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Integer> {

    ProdutoEntity findByCodBarra(String codBarra);

    @Query("SELECT p FROM produto p WHERE p.loja.lojaId = :lojaId AND p.codBarra = :codBarra")
    ProdutoEntity findByCodBarraAndLojaId(@Param("codBarra") String codBarra, @Param("lojaId") Integer lojaId);

        @Query(value = "from produto p WHERE " +
            "(:codBarra IS NULL OR p.codBarra LIKE cast(:codBarra||'%' as text)) " +
            "AND (:descricao IS NULL OR UPPER(p.descricao) LIKE cast('%'||UPPER(cast(:descricao as text))||'%' as text)) " +
            "AND (:lojaId IS NULL OR p.loja.lojaId = :lojaId) " +
            "AND (:produtoId IS NULL OR p.produtoId = :produtoId)")
    Page<ProdutoEntity> findByFilters(@Param("codBarra") String codBarra,
                                      @Param("descricao") String descricao,
                                      @Param("lojaId") Integer lojaId,
                                      @Param("produtoId") Integer produtoId,
                                      Pageable pageable);
}
