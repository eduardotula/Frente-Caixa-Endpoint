package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.filters.CaixaFilter;
import com.loja65.frentecaixa.domain.utils.DefaultTimeZone;
import com.loja65.frentecaixa.outbound.adapters.entity.CaixaEntity;
import com.loja65.frentecaixa.outbound.adapters.mappers.CaixaEntityMapper;
import com.loja65.frentecaixa.outbound.adapters.repositories.CaixaRepository;
import com.loja65.frentecaixa.outbound.port.CaixaPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CaixaAdapter implements CaixaPort {

    @Inject
    CaixaEntityMapper mapper;
    @Inject
    CaixaRepository repository;
    @Inject
    DefaultTimeZone defaultTimeZone;

    @Override
    public Caixa insert(Caixa caixa) {
        return mapper.caixaEntity2Caixa(repository.save(mapper.caixa2CaixaEntity(caixa)));
    }

    @Override
    public Caixa findCaixaByLojaIdAndLocalCaixaId(Integer lojaId, Integer localCaixaId) {
        CaixaEntity caixaEntity = null;
        try {
            caixaEntity = repository.findByLojaLojaIdAndLocalCaixaId(lojaId, localCaixaId);
        } catch (NonUniqueResultException e1){
            throw new IllegalArgumentException("Caixa com localCaixaId já registrado");
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
            LocalDateTime comecoDia = LocalDateTime.of(defaultTimeZone.getSp().toLocalDate(), LocalTime.MIN);
            LocalDateTime finalDia = LocalDateTime.of(defaultTimeZone.getSp().toLocalDate(), LocalTime.MAX);
            caixaEntity = repository.findAllByCreatedAtBetweenAndLojaLojaId(comecoDia,finalDia, lojaId);
        }catch (Exception e){
            throw new IllegalArgumentException("Caixa de loja não encontrado");
        }
        return caixaEntity.stream().map(mapper::caixaEntity2Caixa).collect(Collectors.toList());
    }

    @Override
    public Pagination<Caixa> findByFilters(CaixaFilter filters, PageParam pageParam){
        Pageable pageable = PageRequest.of(pageParam.getPage(), pageParam.getPageSize());

        var page = repository.findByFilters(filters.getDataInicial(), filters.getDataFinal(), filters.getLojaId(), filters.getFuncionario(),
                pageable);

        return new Pagination<Caixa>(pageParam.getPage(), pageParam.getPageSize(),page.getTotalPages(), (int)page.getTotalElements(), pageParam.getSortField(),
                pageParam.getSortType(), page.stream().map(mapper::caixaEntity2Caixa).collect(Collectors.toList()));
    }

    @Override
    public List<String> listAllFuncionarios(){
        return repository.listAllFuncionario();
    }

}
