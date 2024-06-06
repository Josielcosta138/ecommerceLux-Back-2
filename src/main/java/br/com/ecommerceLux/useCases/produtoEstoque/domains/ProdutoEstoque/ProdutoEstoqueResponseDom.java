package br.com.ecommerceLux.useCases.produtoEstoque.domains.ProdutoEstoque;

import br.com.ecommerceLux.entitys.Produto;

public class ProdutoEstoqueResponseDom {

    private Long id;

    private Integer quantidade;

    private String localizacao;

    private Produto produto_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Produto getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
    }
}
