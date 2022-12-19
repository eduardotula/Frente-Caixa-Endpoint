package com.loja65.inbound.port;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Loja;
import com.loja65.domain.model.Venda;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultasPort {

    List<Caixa> getAllCaixaTodayFromLoja(Integer lojaId);

    List<Venda> getAllVendasByFilter(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer lojaId, Pageable pageable);

    LocalDateTime getLastUpdatedByLojaId(Integer lojaId);
}
