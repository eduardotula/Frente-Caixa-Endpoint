package com.loja65.outbound.adapters.mappers;

import com.loja65.domain.model.Loja;
import com.loja65.outbound.adapters.entity.LojaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi",
uses = {ConsultaPrecoProdutoEntityMapper.class})
@ApplicationScoped
public interface LojaEntityMapper {

    Loja lojaEntity2Loja(LojaEntity lojaEntity);
    LojaEntity loja2LojaEntity(Loja loja);

}
