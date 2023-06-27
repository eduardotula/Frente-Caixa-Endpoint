package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.TotalVendasPeriod;
import com.loja65.frentecaixa.domain.model.Venda;
import com.loja65.frentecaixa.domain.model.filters.VendaFilter;
import com.loja65.frentecaixa.outbound.adapters.entity.VendaEntity;
import com.loja65.frentecaixa.outbound.adapters.mappers.VendaEntityMapper;
import com.loja65.frentecaixa.outbound.adapters.repositories.VendaRepository;
import com.loja65.frentecaixa.outbound.port.VendaPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VendaAdapter implements VendaPort {

    @Inject
    VendaRepository repository;
    @Inject
    VendaEntityMapper mapper;

    @Override
    public Venda insert(Venda venda) {
        VendaEntity v = mapper.venda2VendaEntity(venda);
        try{
            v = repository.save(v);
        }catch (Exception e){
            throw new IllegalStateException("Falha ao gravar venda" + venda.getLocalVendaId() +": " + e.getMessage());
        }
        return mapper.vendaEntity2Venda(v);
    }


    @Override
    public void deleteVenda(Venda venda){
        repository.deleteById(venda.getVendaId());
    }

    @Override
    public Pagination<Venda> findByFilters(VendaFilter filters, PageParam pageParam){
        Pageable pageable = PageRequest.of(pageParam.getPage(), pageParam.getPageSize());

        Sort.Direction sortDirection = pageParam.getSortType().equalsIgnoreCase(Sort.Direction.ASC.toString()) ? Sort.Direction.ASC : Sort.Direction.DESC;

        var page = repository.findByFilters(filters.getDataInicial(), filters.getDataFinal(), filters.getLojaId(), filters.getFuncionario(),
                Sort.by(sortDirection,pageParam.getSortField()), pageable);

        return new Pagination<Venda>(pageParam.getPage(), pageParam.getPageSize(),page.getTotalPages(), (int)page.getTotalElements(), pageParam.getSortField(),
                pageParam.getSortType(), page.stream().map(mapper::vendaEntity2Venda).collect(Collectors.toList()));
    }

    @Override
    public List<Venda> findByLojaIdAndlocalVendaId(Integer lojaId, Integer localId){
        List<VendaEntity> vendas = repository.findByLojaIdAndlocalVendaId(lojaId,localId);
        if(vendas.isEmpty()) return new ArrayList<>();
        return vendas.stream().map(mapper::vendaEntity2Venda).collect(Collectors.toList());
    }

    @Override
    public TotalVendasPeriod findTotalVendasPeriodWithFilters(VendaFilter filter){
        try{
            Object[] total = (Object[])repository.getSumVendasPeriodWithFilters(filter.getDataInicial(),filter.getDataFinal(),filter.getLojaId(),filter.getFuncionario());
            return new TotalVendasPeriod((Double)total[0], (Double)total[1], (Double)total[2]);
        }catch (Exception e){
         throw new IllegalStateException(String.format("Falha ao buscar total de vendas err:%s",e.getMessage()));
        }
    }

}
