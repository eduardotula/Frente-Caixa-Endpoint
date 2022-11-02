package com.loja65.outbound.port;

import com.loja65.domain.model.Loja;

import java.util.List;

public interface LojaPort {

    Loja findLojaById(Integer id);
    Loja insert(Loja loja);

    List<Loja> listAll();
}
