package br.com.ecommerceLux.useCases.clientes.domains;

import br.com.ecommerceLux.entitys.Endereco;
import br.com.ecommerceLux.entitys.PedidoVenda;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class ClientesRequestDom {

    private String nome;

    private String sobrenome;

    private String email;

    private LocalDate dataNascimento;

    private String documento;

    private String senha;
    List<Endereco> enderecos;

    List<PedidoVenda> pedidoVendas;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<PedidoVenda> getPedidoVendas() {
        return pedidoVendas;
    }

    public void setPedidoVendas(List<PedidoVenda> pedidoVendas) {
        this.pedidoVendas = pedidoVendas;
    }
}
