package br.com.ecommerceLux.useCases.boleto.domains;

import br.com.ecommerceLux.entitys.PedidoVenda;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BoletoResponseDom {

    private Long id;
    private String codigoBarras;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    PedidoVenda pedidoVenda;
    private Long pedido_venda_id;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }


    @JsonIgnore
    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }



}
