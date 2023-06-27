package com.utils;

import com.loja65.frentecaixa.outbound.adapters.repositories.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseCleaner {

    @Inject
    LojaRepository lojaRepository;
    @Inject
    CaixaRepository caixaRepository;
    @Inject
    VendaRepository vendaRepository;
    @Inject
    OperacaoCaixaRepository operacaoCaixaRepository;
    @Inject
    ConsultaPrecoProdutoRepository consultaPrecoProdutoRepository;
    @Inject
    ProdutoRepository produtoRepository;
    @Inject
    UserAuthRespository userAuthRespository;

    @Transactional
    public void cleanAll(){
        lojaRepository.deleteAll();
        caixaRepository.deleteAll();
        vendaRepository.deleteAll();
        operacaoCaixaRepository.deleteAll();
        consultaPrecoProdutoRepository.deleteAll();
        produtoRepository.deleteAll();
        userAuthRespository.deleteAll();
    }

    @Transactional
    public void cleanLoja(){
        lojaRepository.deleteAll();
    }

    @Transactional
    public void cleanCaixa(){
        caixaRepository.deleteAll();
    }

    @Transactional
    public void cleanVenda(){
        vendaRepository.deleteAll();
    }

    @Transactional
    public void cleanOperacaoCaixa(){
        operacaoCaixaRepository.deleteAll();
    }

    @Transactional
    public void cleanConsultaPrecoProduto(){
        consultaPrecoProdutoRepository.deleteAll();
    }

    @Transactional
    public void cleanProduto(){
        produtoRepository.deleteAll();
    }

    @Transactional
    public void cleanUserAuth(){
        userAuthRespository.deleteAll();
    }
}
