package com.loja65.alarmintelbras.outbound.adapter.repositories;

import com.loja65.alarmintelbras.outbound.adapter.entity.CentralScheduleJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CentralScheduleJobRepository extends JpaRepository<CentralScheduleJobEntity, Integer> {
}
