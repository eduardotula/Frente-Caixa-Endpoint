package com.loja65.domain.usecase;

import com.loja65.domain.model.*;
import com.loja65.domain.model.filters.ProdutoFilter;
import com.loja65.domain.utils.DefaultTimeZone;
import com.loja65.inbound.port.ProdutoDtoPort;
import com.loja65.outbound.port.ConsultaPrecoProdutoPort;
import com.loja65.outbound.port.LojaPort;
import com.loja65.outbound.port.ProdutoPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProdutoUseCase implements ProdutoDtoPort {

    @Inject
    ProdutoPort produtoPort;
    @Inject
    LojaPort lojaPort;
    @Inject
    DefaultTimeZone defaultTimeZone;
    @Inject
    ConsultaPrecoProdutoPort consultaPrecoProdutoPort;

    @Override
    @Transactional
    public Produto createUpdateProduto(Produto produto) {
        if (Objects.isNull(lojaPort.findLojaById(produto.getLojaId())))
            throw new IllegalArgumentException(String.format("Loja não encontrada com id: %d", produto.getLojaId()));

        Produto finalProduct;
        if (Objects.nonNull(produto.getProdutoId()) && produto.getProdutoId() > 0) {

            var existProd = produtoPort.findById(produto.getProdutoId());
            if (Objects.isNull(existProd))
                finalProduct = createProduto(produto);
            else finalProduct = updateProduto(produto, existProd);

        } else finalProduct = createProduto(produto);

        return finalProduct;
    }

    private Produto updateProduto(Produto produto, Produto oldProd) throws IllegalArgumentException{
        var sameCodProdutos = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), produto.getLojaId());

            //Verificar se já existe um produto com o mesmo código de barras á ser atualizado
        if (!sameCodProdutos.isEmpty() && !Objects.equals(produto.getProdutoId(), sameCodProdutos.get(0).getProdutoId())){
            var loja= lojaPort.findLojaById(produto.getLojaId());
            throw new IllegalArgumentException(String.format("Codigo de barras já utilizado por produto: %s em loja: %s", sameCodProdutos.get(0).getDescricao(), loja.getNome()));
        }

        produto.update(oldProd);

        return produtoPort.insert(produto);
    }

    private Produto createProduto(Produto produto) {
        var existProds = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), produto.getLojaId());

        if (existProds.isEmpty()) produto.create(defaultTimeZone.getSp(), produto.getLojaId());
        else produto.update(existProds.get(0));

        return produtoPort.insert(produto);
    }

    @Override
    public List<Produto> findByCodbarra(String codBarra) {
        List<Produto> produtos = produtoPort.findByCodBarra(codBarra);
        if (Objects.isNull(produtos) || produtos.size() == 0)
            throw new IllegalStateException(String.format("Produto não encontrado com codBarra: %s", codBarra));
        return produtos;
    }

    @Override
    public Pagination<Produto> findbyFilters(PageParam params, ProdutoFilter produtoFilter) {
        return produtoPort.findByFilters(params, produtoFilter);
    }

    @Override
    @Transactional
    public List<ConsultaPrecoProduto> getUpdatedsProdutosPrecosByLoja(Integer lojaId) {
        Loja loja = lojaPort.findLojaById(lojaId);
        if (Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

        List<ConsultaPrecoProduto> produtosAtt = consultaPrecoProdutoPort.findByLojaLojaId(lojaId);

        consultaPrecoProdutoPort.deleteAllByLoja(lojaId);

        return produtosAtt;
    }

    @Override
    @Transactional
    public void addUpdatedProdutosPrecosByDiferentLoja(ConsultaPrecoProduto consultaPrecoProduto, List<Integer> lojaIds) {
        List<Loja> insertLojas = new ArrayList<>();
        List<Loja> allLojas = lojaPort.listAll();

        allLojas.stream().forEach(l -> {
            if (!lojaIds.contains(l.getLojaId())) insertLojas.add(l);
        });

        if (insertLojas.isEmpty()) throw new IllegalArgumentException("Não existem outras lojas cadastradas");

        insertLojas.forEach(lojaConsultaInsert -> {

            Produto produto = consultaPrecoProduto.getProduto();
            List<Produto> existProdutos = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), lojaConsultaInsert.getLojaId());
            Produto existProduto;

            if (Objects.isNull(existProdutos) || existProdutos.isEmpty()) {
                produto.create(defaultTimeZone.getSp(), lojaConsultaInsert.getLojaId());
                produto.setValor(consultaPrecoProduto.getNewValue());
                consultaPrecoProduto.setProduto(produtoPort.insert(produto));

            } else {
                existProduto = existProdutos.get(0);
                existProduto.setDescricao(consultaPrecoProduto.getProduto().getDescricao());
                existProduto.setValor(consultaPrecoProduto.getNewValue());
                produtoPort.insert(existProduto);
                consultaPrecoProduto.setProduto(existProduto);
            }

            consultaPrecoProduto.setLoja(lojaConsultaInsert);
            consultaPrecoProdutoPort.insert(consultaPrecoProduto);
        });

    }
}
