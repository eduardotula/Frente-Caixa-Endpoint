package com.loja65.alarmintelbras.outbound.adapter.repositories;

import com.loja65.alarmintelbras.outbound.adapter.entity.CentralEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface CentralRepository extends JpaRepository<CentralEntity, Integer> {

    List<CentralEntity> findByDescricao(String descricao);
    CentralEntity findByCentralMac(String centralMac);
}
