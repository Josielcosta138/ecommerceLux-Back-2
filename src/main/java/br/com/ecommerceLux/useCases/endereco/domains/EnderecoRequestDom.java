package br.com.ecommerceLux.useCases.endereco.domains;

import br.com.ecommerceLux.entitys.Clientes;

public class EnderecoRequestDom {

    private String rua;

    private String bairro;

    private String cidade;

    private String estado;

    private Clientes clientes_id;

    private Long idCliente;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Clientes getClientes_id() {
        return clientes_id;
    }

    public void setClientes_id(Clientes clientes_id) {
        this.clientes_id = clientes_id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
