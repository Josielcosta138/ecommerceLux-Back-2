package br.com.ecommerceLux.useCases.pix.domains;

import br.com.ecommerceLux.entitys.PedidoVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PixRequestDom {

    private String chavePix;
    private LocalDate dataTransacao;
    private BigDecimal valorTransacao;
    PedidoVenda pedidoVenda;
    private Long pedido_venda_id;

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(BigDecimal valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public Long getPedido_venda_id() {
        return pedido_venda_id;
    }

    public void setPedido_venda_id(Long pedido_venda_id) {
        this.pedido_venda_id = pedido_venda_id;
    }
}
