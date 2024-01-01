package com.loja65.frentecaixa.outbound.adapters.repositories;

import com.loja65.frentecaixa.outbound.adapters.entity.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<LojaEntity, Integer> {

    LojaEntity findLojaBylojaId(Integer id);


}
