package com.loja65.inbound.port;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Venda;
import com.loja65.inbound.dto.consulta.VendaConsultaDto;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface ConsultasPort {

    List<Caixa> getAllCaixaTodayFromLoja(Integer lojaId);

    }
