package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.domain.model.Loja;

import java.util.List;

public interface LojaPort {

    Loja findLojaById(Integer id);
    Loja insert(Loja loja);

    Loja update(Loja loja);

    List<Loja> listAll();
}
