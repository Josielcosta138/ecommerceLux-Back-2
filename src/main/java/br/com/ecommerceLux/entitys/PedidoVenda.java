package br.com.ecommerceLux.entitys;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PedidoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private Double totalPedido;

    @ManyToOne
    @JoinColumn(name = "clientes_id", nullable = false)
    private Clientes clientes;

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "pedido_venda_item_id", nullable = false)
    private PedidoVendaItem pedidoVendaItem;

    @OneToOne
    @JoinColumn(name = "forma_pagto_id", nullable = false)
    private FormaPagamento formaPagamento;

    @OneToOne(mappedBy = "pedidoVenda")
    Boleto boleto;

    @OneToOne(mappedBy = "pedidoVenda")
    Pix pix;

    @OneToOne(mappedBy = "pedidoVenda")
    Cartao cartao;


    public Pix getPix() {
        return pix;
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

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

}
