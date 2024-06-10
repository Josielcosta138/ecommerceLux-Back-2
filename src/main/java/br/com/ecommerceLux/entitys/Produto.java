package br.com.ecommerceLux.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import javax.validation.constraints.Size;
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

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, length = 3, columnDefinition = "varchar(3) default 'P'")
    @Size(min = 1, max = 3)
    private String tamanho;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<PedidoVendaItem> pedidoVendaItens;

    @Column(nullable = true)
    private String enderecoImagem;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoEstoque> produtoEstoques;

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public void setEnderecoImagem(String enderecoImagem) {
        this.enderecoImagem = enderecoImagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

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
