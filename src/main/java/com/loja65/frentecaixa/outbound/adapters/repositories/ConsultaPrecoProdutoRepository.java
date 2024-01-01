package com.loja65.frentecaixa.outbound.adapters.repositories;

import com.loja65.frentecaixa.outbound.adapters.entity.ConsultaPrecoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface ConsultaPrecoProdutoRepository extends JpaRepository<ConsultaPrecoProdutoEntity, Integer> {

    List<ConsultaPrecoProdutoEntity> findByLojaLojaId(Integer lojaId);
    @Query("SELECT c FROM consultaPrecoProduto c WHERE c.loja.lojaId != :lojaId")
    List<ConsultaPrecoProdutoEntity> findDistinctByLojaLojaId(@Param("lojaId") Integer lojaId);


}
