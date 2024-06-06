package br.com.ecommerceLux.useCases;


import br.com.ecommerceLux.entitys.Clientes;
import br.com.ecommerceLux.entitys.Endereco;
import br.com.ecommerceLux.repositorys.ClientesRepository;
import br.com.ecommerceLux.repositorys.EnderecoRepository;
import br.com.ecommerceLux.useCases.endereco.domains.EnderecoRequestDom;
import br.com.ecommerceLux.useCases.endereco.domains.EnderecoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoResponseDom> carregarEnderecos(){
        List<Endereco> resultadoDeEnderecosPostamn = enderecoRepository.findAll();

        List<EnderecoResponseDom> listaDeEnderecos = new ArrayList<>();

            for (Endereco dadoResultado: resultadoDeEnderecosPostamn) {

                EnderecoResponseDom aux = new EnderecoResponseDom();
                aux.setId(dadoResultado.getId());
                aux.setRua(dadoResultado.getRua());
                aux.setBairro(dadoResultado.getBairro());
                aux.setCidade(dadoResultado.getCidade());
                aux.setEstado(dadoResultado.getEstado());
                aux.setClientes_id(dadoResultado.getClientes());

                listaDeEnderecos.add(aux);
            }

        return listaDeEnderecos;
    }



    public List<EnderecoResponseDom> carregarEnderecosByIdCliente(Long id){
        List<Endereco> resultadoDeEnderecosPostamn = enderecoRepository.findByClientesId(id);
        List<EnderecoResponseDom> listaDeEnderecos = new ArrayList<>();

        for (Endereco dadoResultado: resultadoDeEnderecosPostamn) {

                EnderecoResponseDom aux = new EnderecoResponseDom();
                aux.setId(dadoResultado.getId());
                aux.setRua(dadoResultado.getRua());
                aux.setBairro(dadoResultado.getBairro());
                aux.setCidade(dadoResultado.getCidade());
                aux.setEstado(dadoResultado.getEstado());
                aux.setClientes_id(dadoResultado.getClientes());

                listaDeEnderecos.add(aux);
            }
        return listaDeEnderecos;
    }



    public EnderecoResponseDom carregarEnderecosById(Long id){
        Optional<Endereco> resultado = enderecoRepository.findById(id);

        if (resultado.isPresent()){
            Endereco enderecos = resultado.get();

            EnderecoResponseDom responseDOM = new EnderecoResponseDom();
            responseDOM.setId(enderecos.getId());
            responseDOM.setRua(enderecos.getRua());
            responseDOM.setBairro(enderecos.getBairro());
            responseDOM.setCidade(enderecos.getCidade());
            responseDOM.setEstado(enderecos.getEstado());
            responseDOM.setClientes_id(enderecos.getClientes());
            return responseDOM;
        }
        return null;
    }


    public EnderecoResponseDom criarEndereco(EnderecoRequestDom endereco) throws CrudException {
        List<String>mensagens = this.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

        Optional<Clientes> resultadoIdClientePostman = clientesRepository.findById(endereco.getIdCliente());

        List<Endereco> listaEnderecos = enderecoRepository.findByClientesId(endereco.getIdCliente());

        if (listaEnderecos.size() >= 5) {
            throw new CrudException(" Cliente já possui 5 endereços cadastrados, não é" +
                    " permitido adicionar mais endereços!: " );
        }

        else {
            Endereco enderecosEntidades = new Endereco();
            enderecosEntidades.setRua(endereco.getRua());
            enderecosEntidades.setBairro(endereco.getBairro());
            enderecosEntidades.setCidade(endereco.getCidade());
            enderecosEntidades.setEstado(endereco.getEstado());
            enderecosEntidades.setClientes(resultadoIdClientePostman.get());

            Endereco resultado = enderecoRepository.save(enderecosEntidades);

            EnderecoResponseDom responseDOM = new EnderecoResponseDom();
            responseDOM.setId(resultado.getId());
            responseDOM.setClientes_id(resultado.getClientes());
            responseDOM.setRua(resultado.getRua());
            responseDOM.setBairro(resultado.getBairro());
            responseDOM.setCidade(resultado.getCidade());
            responseDOM.setEstado(resultado.getEstado());

            return responseDOM;
        }
    }


    // --------------------- TESTE
    public EnderecoResponseDom criarEnderecoPorId(EnderecoRequestDom endereco) throws CrudException {
        List<String>mensagens = this.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }
        Optional<Clientes> resultadoIdClientePostman = clientesRepository.findById(endereco.getIdCliente());

            List<Endereco> listaEnderecos = enderecoRepository.findByClientesId(endereco.getIdCliente());

        if (listaEnderecos.size() >= 5) {
            throw new CrudException(" Cliente já possui 5 endereços cadastrados, não é" +
                    " permitido adicionar mais endereços!: " );
        }

        else {
            Endereco enderecosEntidades = new Endereco();
            enderecosEntidades.setRua(endereco.getRua());
            enderecosEntidades.setBairro(endereco.getBairro());
            enderecosEntidades.setCidade(endereco.getCidade());
            enderecosEntidades.setEstado(endereco.getEstado());
            enderecosEntidades.setClientes(resultadoIdClientePostman.get());
            Endereco resultado = enderecoRepository.save(enderecosEntidades);

            EnderecoResponseDom responseDOM = new EnderecoResponseDom();
            responseDOM.setId(resultado.getId());
            responseDOM.setClientes_id(resultado.getClientes());
            responseDOM.setRua(resultado.getRua());
            responseDOM.setBairro(resultado.getBairro());
            responseDOM.setCidade(resultado.getCidade());
            responseDOM.setEstado(resultado.getEstado());

            return responseDOM;
        }
    }

    public EnderecoResponseDom atualizarEndereco(Long id, EnderecoRequestDom enderecos) throws CrudException{

        List<String>mensagens = this.validarEndereco(enderecos);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

        Optional<Clientes> resultadoIdClientePostman = clientesRepository.findById(enderecos.getIdCliente());
        List<Endereco> listaEnderecos = enderecoRepository.findByClientesId(enderecos.getIdCliente());

        if (listaEnderecos.size() >= 5) {
            throw new CrudException(" Cliente já possui 5 endereços cadastrados, não é" +
                    " permitido atualizar mais endereços pare esse cliente!: " );
        }
        else {
            // Verifica se o Optional de Clientes contém um valor
            if (resultadoIdClientePostman.isPresent()) {
                Optional<Endereco> resultado = enderecoRepository.findById(id).map(record -> {
                    record.setRua(enderecos.getRua());
                    record.setBairro(enderecos.getBairro());
                    record.setCidade(enderecos.getCidade());
                    record.setEstado(enderecos.getEstado());
                    record.setClientes(resultadoIdClientePostman.get());
                    return enderecoRepository.save(record);
                });

                if (resultado.isPresent()){
                    Endereco enderecosEntidades = resultado.get();

                    EnderecoResponseDom responseDOM = new EnderecoResponseDom();
                    responseDOM.setId(enderecosEntidades.getId());
                    responseDOM.setRua(enderecosEntidades.getRua());
                    responseDOM.setBairro(enderecosEntidades.getBairro());
                    responseDOM.setCidade(enderecosEntidades.getCidade());
                    responseDOM.setEstado(enderecosEntidades.getEstado());
                    responseDOM.setClientes_id(enderecosEntidades.getClientes());

                    return responseDOM;
                }
            } else {
                throw new RuntimeException("Cliente não encontrado pelo ID fornecido: " + enderecos.getIdCliente());
            }
            return null;
        }




    }

    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    private List<String> validarEndereco(EnderecoRequestDom endereco) {
        List<String> mensagens = new ArrayList<>();

        if (endereco.getBairro() == null || endereco.getBairro().equals("")) {
            mensagens.add("bairro não informado");
        }
        if (endereco.getCidade() == null || endereco.getCidade().equals("")) {
            mensagens.add("cidade não informado");
        }
        if (endereco.getEstado() == null || endereco.getEstado().equals("")) {
            mensagens.add("estado não informado");
        }
        if (endereco.getRua() == null || endereco.getRua().equals("")) {
            mensagens.add("rua não informado");
        }
        return mensagens;

    }


}
