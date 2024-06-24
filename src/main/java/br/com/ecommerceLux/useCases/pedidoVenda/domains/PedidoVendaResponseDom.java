package br.com.ecommerceLux.useCases.pedidoVenda.domains;

import br.com.ecommerceLux.entitys.*;
import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemResponseDom;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

public class PedidoVendaResponseDom {

    private Long id;
    private LocalDate dataPedido;
    private String cliente;
    private Double totalPedido;
    private Clientes clientes;
    private Endereco endereco;
    private PedidoVendaItem pedidoVendaItem;
    private FormaPagamento formaPagamento;
    private List<PedidoVendaItemResponseDom> itensPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

//    @JsonIgnore
    public PedidoVendaItem getPedidoVendaItem() {
        return pedidoVendaItem;
    }

    public void setPedidoVendaItem(PedidoVendaItem pedidoVendaItem) {
        this.pedidoVendaItem = pedidoVendaItem;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setItensPedido(List<PedidoVendaItemResponseDom> itensResponse) {
        this.itensPedido = itensResponse;
    }

    public List<PedidoVendaItemResponseDom> getItensPedido() {
        return itensPedido;
    }
}
