package com.loja65.outbound.adapters.repositories;

import com.loja65.outbound.adapters.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    ProdutoEntity findByCodBarra(String codBarra);
}
