package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.Venda;
import com.loja65.outbound.adapters.entity.VendaEntity;
import com.loja65.outbound.adapters.mappers.VendaEntityMapper;
import com.loja65.outbound.adapters.repositories.VendaRepository;
import com.loja65.outbound.port.VendaPort;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    public List<Venda> getVendasByFilter(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer lojaId, Pageable pageable) {
        return repository.findAllByCreatedAtBetweenAndLojaLojaId(dataInicial,dataFinal,lojaId, pageable).stream().map(mapper::vendaEntity2Venda).collect(Collectors.toList());
    }

    @Override
    public void deleteVendaByLocalId(Integer lojaId, Integer localId) {
        List<VendaEntity> venda = repository.findByLojaIdAndlocalVendaId(lojaId,localId);
        if(Objects.isNull(venda)) throw new IllegalStateException("Vendas não encontrada com ids");
        venda.forEach(v -> repository.deleteById(v.getVendaId()));
    }

}
