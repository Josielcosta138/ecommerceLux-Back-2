package br.com.ecommerceLux.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<PedidoVendaItem> pedidoVendaItens;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoEstoque> produtoEstoques;


    @JsonIgnore
    public List<ProdutoEstoque> getProdutoEstoques() {
        return produtoEstoques;
    }

    public void setProdutoEstoques(List<ProdutoEstoque> produtoEstoques) {
        this.produtoEstoques = produtoEstoques;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

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

    public List<PedidoVendaItem> getPedidoVendaItens() {
        return pedidoVendaItens;
    }

    public void setPedidoVendaItens(List<PedidoVendaItem> pedidoVendaItens) {
        this.pedidoVendaItens = pedidoVendaItens;
    }
}
