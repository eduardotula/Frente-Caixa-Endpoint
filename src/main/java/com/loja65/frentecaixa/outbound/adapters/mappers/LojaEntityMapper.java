package com.loja65.frentecaixa.outbound.adapters.mappers;

import com.loja65.frentecaixa.domain.model.Loja;
import com.loja65.frentecaixa.outbound.adapters.entity.LojaEntity;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi",
uses = {ConsultaPrecoProdutoEntityMapper.class})
@ApplicationScoped
public interface LojaEntityMapper {

    Loja lojaEntity2Loja(LojaEntity lojaEntity);
    LojaEntity loja2LojaEntity(Loja loja);

}
