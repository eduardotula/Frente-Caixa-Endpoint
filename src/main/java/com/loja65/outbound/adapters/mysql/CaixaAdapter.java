package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.Caixa;
import com.loja65.outbound.adapters.entity.CaixaEntity;
import com.loja65.outbound.adapters.mappers.CaixaEntityMapper;
import com.loja65.outbound.adapters.repositories.CaixaRepository;
import com.loja65.outbound.port.CaixaPort;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CaixaAdapter implements CaixaPort {

    @Inject
    CaixaEntityMapper mapper;
    @Inject
    CaixaRepository repository;

    @Override
    public Caixa insert(Caixa caixa) {
        return mapper.caixaEntity2Caixa(repository.save(mapper.caixa2CaixaEntity(caixa)));
    }

    @Override
    public Caixa findLastCaixaByLojaId(Integer lojaId) {
        CaixaEntity caixaEntity = null;
        try{
            caixaEntity = repository.findFistByLojaLojaIdOrderByCaixaIdDesc(PageRequest.of(0,1),lojaId);
        }catch (Exception e){
            throw new IllegalArgumentException("Caixa de loja não encontrado");
        }
        return mapper.caixaEntity2Caixa(caixaEntity);
    }

    @Override
    public Caixa findByCaixaId(Integer caixaId) {
        return mapper.caixaEntity2Caixa(repository.findByCaixaId(caixaId));
    }

    @Override
    public List<Caixa> findAllTodayByLojaId(Integer lojaId) {
        List<CaixaEntity> caixaEntity = new ArrayList<>();
        try{
            LocalDateTime comecoDia = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            LocalDateTime finalDia = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            caixaEntity = repository.findAllByCreatedAtBetweenAndLojaLojaId(comecoDia,finalDia, lojaId);
        }catch (Exception e){
            throw new IllegalArgumentException("Caixa de loja não encontrado");
        }
        return mapper.caixaEntity2Caixa(caixaEntity);
    }


}
