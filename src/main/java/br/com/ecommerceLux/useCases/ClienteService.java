package br.com.ecommerceLux.useCases;


import br.com.ecommerceLux.entitys.Clientes;
import br.com.ecommerceLux.repositorys.ClientesRepository;
import br.com.ecommerceLux.repositorys.EnderecoRepository;
import br.com.ecommerceLux.useCases.clientes.domains.ClientesResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<ClientesResponseDom> carregarClientes() {

        List<Clientes> listaDeClientes = clientesRepository.findAll(); //Lista clientes repository
        List<ClientesResponseDom> clientes = new ArrayList<>();

        for (Clientes resultadoClientes : listaDeClientes) {

            ClientesResponseDom aux = new ClientesResponseDom();
            aux.setId(resultadoClientes.getId());
            aux.setNome(resultadoClientes.getNome());
            aux.setSobrenome(resultadoClientes.getSobrenome());
            aux.setDocumento(resultadoClientes.getDocumento());
            aux.setEmail(resultadoClientes.getEmail());
            aux.setDataNascimento(resultadoClientes.getDataNascimento());
            clientes.add(aux);
        }

        return clientes;
    }




    public ClientesResponseDom carregarClienteById(Long id){
        Optional<Clientes> resultado = clientesRepository.findById(id);

        if (resultado.isPresent()){
            Clientes clientes = resultado.get();

            ClientesResponseDom responseDOM = new ClientesResponseDom();
            responseDOM.setId(clientes.getId());
            responseDOM.setNome(clientes.getNome());
            responseDOM.setEmail(clientes.getEmail());
            responseDOM.setDataNascimento(clientes.getDataNascimento());
            responseDOM.setDocumento(clientes.getDocumento());
            responseDOM.setSobrenome(clientes.getSobrenome());
            return responseDOM;
        }
        return null;
    }

    public ClientesResponseDom criarClientes(ClientesResponseDom cliente) throws CrudException {
        List<String> mensagens = this.validarCliente(cliente);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

        Clientes clientesEntidades = new Clientes();
        clientesEntidades.setNome(cliente.getNome());
        clientesEntidades.setEmail(cliente.getEmail());
        clientesEntidades.setDocumento(cliente.getDocumento());;
        clientesEntidades.setSobrenome(cliente.getSobrenome());
        clientesEntidades.setDataNascimento(cliente.getDataNascimento());
        Clientes resultado = clientesRepository.save(clientesEntidades);

        ClientesResponseDom responseDOM = new ClientesResponseDom();
        responseDOM.setId(resultado.getId());
        responseDOM.setNome(resultado.getNome());
        responseDOM.setSobrenome(resultado.getSobrenome());
        responseDOM.setEmail(resultado.getEmail());
        responseDOM.setDataNascimento(resultado.getDataNascimento());
        responseDOM.setDocumento(resultado.getDocumento());

        return responseDOM;
    }


    public ClientesResponseDom atualizarCliente(Long id, ClientesResponseDom cliente){
        Optional<Clientes> resultado = clientesRepository.findById(id).map(record -> {
            record.setNome(cliente.getNome());
            record.setSobrenome(cliente.getSobrenome());
            record.setEmail(cliente.getEmail());
            record.setDataNascimento(cliente.getDataNascimento());
            record.setDocumento(cliente.getDocumento());

            return clientesRepository.save(record);
        });

        if (resultado.isPresent()){
            Clientes clientesEntidades = resultado.get();

            ClientesResponseDom responseDOM = new ClientesResponseDom();
            responseDOM.setId(clientesEntidades.getId());
            responseDOM.setNome(clientesEntidades.getNome());
            responseDOM.setSobrenome(clientesEntidades.getSobrenome());
            responseDOM.setEmail(clientesEntidades.getEmail());
            responseDOM.setDataNascimento(clientesEntidades.getDataNascimento());
            responseDOM.setDocumento(clientesEntidades.getDocumento());

            return responseDOM;
        }
        return null;
    }


    public void excluirCliente(Long id){
        clientesRepository.deleteById(id);
    }


    public List<String> validarCliente(ClientesResponseDom cliente){
        List<String> mensagens = new ArrayList<>();

        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            mensagens.add("nome do cliente não informado");
        }
        return mensagens;
    }



}
