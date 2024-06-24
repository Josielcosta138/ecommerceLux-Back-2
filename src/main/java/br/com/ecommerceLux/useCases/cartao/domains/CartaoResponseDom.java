package br.com.ecommerceLux.useCases.cartao.domains;

import br.com.ecommerceLux.entitys.PedidoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CartaoResponseDom {

    private Long id;
    private int numeroCartao;
    private String nomeTitular;
    private LocalDate validade;
    private String cvv;
    private PedidoVenda pedidoVenda;
    private Long pedido_venda_id;
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getPedido_venda_id() {
        return pedido_venda_id;
    }

    public void setPedido_venda_id(Long pedido_venda_id) {
        this.pedido_venda_id = pedido_venda_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }
}
