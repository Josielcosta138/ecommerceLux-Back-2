package br.com.ecommerceLux.useCases.pedidoVendaItem.domains;

import br.com.ecommerceLux.entitys.PedidoVenda;
import br.com.ecommerceLux.entitys.Produto;

public class PedidoVendaItemResponseDom {
    private Long id;
    private PedidoVenda pedidoVenda;
    private Produto produto;
    private Integer quantidade;
    private Double preco;

    private long pedidoVendaId;

    public long getPedidoVendaId() {
        return pedidoVendaId;
    }

    public void setPedidoVendaId(long pedidoVendaId) {
        this.pedidoVendaId = pedidoVendaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

//    public void setPedidoVendaId(Long pedidoVendaId) {
//    }
}
