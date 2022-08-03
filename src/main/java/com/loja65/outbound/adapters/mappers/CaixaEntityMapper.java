package com.loja65.outbound.adapters.mappers;

import com.loja65.domain.model.Caixa;
import com.loja65.outbound.adapters.entity.CaixaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface CaixaEntityMapper {

    @Mapping(source = "loja.lojaId", target = "lojaId")
    Caixa caixaEntity2Caixa(CaixaEntity caixaEntity);
    @Mapping(source = "lojaId", target = "loja.lojaId")
    CaixaEntity caixa2CaixaEntity(Caixa caixa);

    @Mapping(source = "loja.lojaId", target = "lojaId")
    List<Caixa> caixaEntity2Caixa(List<CaixaEntity> caixaEntity);
    @Mapping(source = "lojaId", target = "loja.lojaId")
    List<CaixaEntity> caixa2CaixaEntity(List<Caixa> caixa);
}
