package com.loja65.inbound.port;

import com.loja65.domain.model.Caixa;

import java.util.List;

public interface ConsultasPort {

    List<Caixa> getAllCaixaTodayFromLoja(Integer lojaId);

    }
