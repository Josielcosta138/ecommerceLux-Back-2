package br.com.ecommerceLux.useCases.produto.domains;

import br.com.ecommerceLux.entitys.Categoria;
import br.com.ecommerceLux.entitys.PedidoVendaItem;
import br.com.ecommerceLux.entitys.ProdutoEstoque;
import java.util.List;

public class ProdutoRequestDom {
    private String nome;
    private Double preco;
    private Categoria categoria;
    private List<PedidoVendaItem> pedidoVendaItens;
    private List<ProdutoEstoque> produtoEstoques;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<PedidoVendaItem> getPedidoVendaItens() {
        return pedidoVendaItens;
    }

    public void setPedidoVendaItens(List<PedidoVendaItem> pedidoVendaItens) {
        this.pedidoVendaItens = pedidoVendaItens;
    }

    public List<ProdutoEstoque> getProdutoEstoques() {
        return produtoEstoques;
    }

    public void setProdutoEstoques(List<ProdutoEstoque> produtoEstoques) {
        this.produtoEstoques = produtoEstoques;
    }
}
