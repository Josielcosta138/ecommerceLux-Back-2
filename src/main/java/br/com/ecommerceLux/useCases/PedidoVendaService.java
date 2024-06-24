package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.*;
import br.com.ecommerceLux.repositorys.*;
import br.com.ecommerceLux.useCases.pedidoVenda.domains.PedidoVendaRequestDom;
import br.com.ecommerceLux.useCases.pedidoVenda.domains.PedidoVendaResponseDom;
import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemRequestDom;
import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemResponseDom;
import br.com.ecommerceLux.useCases.pix.domains.PixResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PedidoVendaService {

    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;
    @Autowired
    private PedidoVendaItemRepository pedidoVendaItemRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private PedidoVendaItemService pedidoVendaItemService;


    public List<PedidoVendaResponseDom> carregarPedidosVenda() {
        List<PedidoVenda> listaDePedidos = pedidoVendaRepository.findAll();
        List<PedidoVendaResponseDom> pedidos = new ArrayList<>();


        for (PedidoVenda pedido : listaDePedidos) {
            PedidoVendaResponseDom aux = new PedidoVendaResponseDom();
            aux.setId(pedido.getId());
            aux.setDataPedido(pedido.getDataPedido());
            aux.setCliente(pedido.getCliente());
            aux.setTotalPedido(pedido.getTotalPedido());
            aux.setClientes(pedido.getClientes());
            aux.setEndereco(pedido.getEndereco());
            aux.setFormaPagamento(pedido.getFormaPagamento());

            pedidos.add(aux);
        }

        return pedidos;
    }

    public PedidoVendaResponseDom carregarPedidoVendaById(Long id) {
        Optional<PedidoVenda> resultado = pedidoVendaRepository.findById(id);

        if (resultado.isPresent()) {
            PedidoVenda pedido = resultado.get();
            PedidoVendaResponseDom responseDOM = new PedidoVendaResponseDom();
            responseDOM.setId(pedido.getId());
            responseDOM.setDataPedido(pedido.getDataPedido());
            responseDOM.setCliente(pedido.getCliente());
            responseDOM.setTotalPedido(pedido.getTotalPedido());
            responseDOM.setClientes(pedido.getClientes());
            responseDOM.setEndereco(pedido.getEndereco());
            responseDOM.setFormaPagamento(pedido.getFormaPagamento());
            return responseDOM;
        }
        return null;
    }


    public PedidoVendaResponseDom criarPedidoVenda(PedidoVendaRequestDom pedidoRequest) throws CrudException {
        List<String> mensagens = this.validarPedidoVenda(pedidoRequest);
        if (!mensagens.isEmpty()) {
            throw new CrudException(mensagens);
        }

        // Criar o pedido de venda
        PedidoVenda pedidoEntidade = new PedidoVenda();
        pedidoEntidade.setDataPedido(pedidoRequest.getDataPedido());
        pedidoEntidade.setCliente(pedidoRequest.getCliente());
        pedidoEntidade.setTotalPedido(pedidoRequest.getTotalPedido());

        // Recuperar objetos associados pelos IDs
            Optional<Clientes> clientesOptional = clientesRepository.findById(pedidoRequest.getClientesId());
            if (!clientesOptional.isPresent()) {
                throw new CrudException(Arrays.asList("Cliente não encontrado"));
            }
                pedidoEntidade.setClientes(clientesOptional.get());



            Optional<Endereco> enderecoOptional = enderecoRepository.findById(pedidoRequest.getEnderecoId());
            if (!enderecoOptional.isPresent()) {
                throw new CrudException(Arrays.asList("Endereço não encontrado"));
            }
                pedidoEntidade.setEndereco(enderecoOptional.get());



            Optional<FormaPagamento> formaPagamentoOptional = formaPagamentoRepository.findById(pedidoRequest.getFormaPagamentoId());
            if (!formaPagamentoOptional.isPresent()) {
                throw new CrudException(Arrays.asList("Forma de Pagamento não encontrada"));
            }
                pedidoEntidade.setFormaPagamento(formaPagamentoOptional.get());


        List<PedidoVendaItemRequestDom> itensRequest = pedidoRequest.getItensPedido();
        Double valorTotalFinalCompra = 0.0;

            for (PedidoVendaItemRequestDom listaItens: itensRequest){
                valorTotalFinalCompra += listaItens.getPreco() * listaItens.getQuantidade();
            }

        pedidoEntidade.setTotalPedido(valorTotalFinalCompra);
        PedidoVenda resultadoResponse = pedidoVendaRepository.save(pedidoEntidade);


        List<PedidoVendaItemResponseDom> itensResponse = pedidoVendaItemService.criarListaPedidoVendaItens(itensRequest, resultadoResponse.getId());



        resultadoResponse.setPedidoVendaItem(itensResponse);
        resultadoResponse = pedidoVendaRepository.save(resultadoResponse);

        // Retornar o response
        PedidoVendaResponseDom responseDOM = new PedidoVendaResponseDom();
        responseDOM.setId(resultadoResponse.getId());
        responseDOM.setDataPedido(resultadoResponse.getDataPedido());
        responseDOM.setCliente(resultadoResponse.getCliente());
        responseDOM.setTotalPedido(resultadoResponse.getTotalPedido());
        responseDOM.setClientes(resultadoResponse.getClientes());
        responseDOM.setEndereco(resultadoResponse.getEndereco());
        responseDOM.setFormaPagamento(resultadoResponse.getFormaPagamento());
        responseDOM.setItensPedido(itensResponse);

        return responseDOM;
    }






    public List<String> validarPedidoVenda(PedidoVendaRequestDom pedido) {
        List<String> mensagens = new ArrayList<>();

        if (pedido.getDataPedido() == null) {
            mensagens.add("Data do pedido não informada");
        }
        if (pedido.getCliente() == null || pedido.getCliente().isEmpty()) {
            mensagens.add("Cliente não informado");
        }
        if (pedido.getTotalPedido() == null || pedido.getTotalPedido() < 0) {
            mensagens.add("Total do pedido inválido");
        }
        if (pedido.getClientesId() == null) {
            mensagens.add("Clientes não informado");
        }
        if (pedido.getEnderecoId() == null) {
            mensagens.add("Endereço não informado");
        }
//        if (pedido.getPedidoVendaItemId() == null) {
//            mensagens.add("Itens do pedido não informados");
//        }
        if (pedido.getFormaPagamentoId() == null) {
            mensagens.add("Forma de pagamento não informada");
        }
        return mensagens;
    }

}
