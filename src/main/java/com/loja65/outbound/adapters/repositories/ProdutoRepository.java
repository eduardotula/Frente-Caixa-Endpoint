package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    ProdutoEntity findByCodBarra(String codBarra);

    @Query("SELECT p FROM produto p WHERE p.loja.lojaId = :lojaId AND p.codBarra = :codBarra")
    ProdutoEntity findByCodBarraAndLojaId(@Param("codBarra") String codBarra,@Param("lojaId") Integer lojaId);
}
