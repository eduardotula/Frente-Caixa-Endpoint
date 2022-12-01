package com.loja65.outbound.port;

import com.loja65.domain.model.Venda;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaPort {

    Venda insert(Venda venda);

    List<Venda> getVendasByFilter(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer lojaId, Pageable pageable);

    void deleteVendaByLocalId(Integer lojaId, Integer localId);

}
