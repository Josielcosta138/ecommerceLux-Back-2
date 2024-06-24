package br.com.ecommerceLux.useCases.pedidoVenda.domains;

import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemRequestDom;

import java.time.LocalDate;
import java.util.List;

public class PedidoVendaRequestDom {

    private LocalDate dataPedido;
    private String cliente;
    private Double totalPedido;
    private Long clientesId;
    private Long enderecoId;
//    private Long pedidoVendaItemId;
    private Long formaPagamentoId;
    private List<PedidoVendaItemRequestDom> itensPedido;

    // Getters e Setters

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Long getClientesId() {
        return clientesId;
    }

    public void setClientesId(Long clientesId) {
        this.clientesId = clientesId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

//    public Long getPedidoVendaItemId() {
//        return pedidoVendaItemId;
//    }
//
//    public void setPedidoVendaItemId(Long pedidoVendaItemId) {
//        this.pedidoVendaItemId = pedidoVendaItemId;
//    }

    public Long getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(Long formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public List<PedidoVendaItemRequestDom> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoVendaItemRequestDom> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
