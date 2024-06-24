package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.Cartao;
import br.com.ecommerceLux.entitys.PedidoVenda;
import br.com.ecommerceLux.repositorys.CartaoRepository;
import br.com.ecommerceLux.repositorys.PedidoVendaRepository;
import br.com.ecommerceLux.useCases.cartao.domains.CartaoRequestDom;
import br.com.ecommerceLux.useCases.cartao.domains.CartaoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {


    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    public List<CartaoResponseDom> carregarCartoes() {
        List<Cartao> listaDeCartoes = cartaoRepository.findAll();
        List<CartaoResponseDom> cartaoList = new ArrayList<>();

        for (Cartao resultadoCartao : listaDeCartoes) {
            CartaoResponseDom aux = new CartaoResponseDom();
            aux.setId(resultadoCartao.getId());
            aux.setNumeroCartao(resultadoCartao.getNumeroCartao());
            aux.setNomeTitular(resultadoCartao.getNomeTitular());
            aux.setValidade(resultadoCartao.getValidade());
            aux.setCvv(resultadoCartao.getCvv());
            aux.setValor(resultadoCartao.getValor());
            aux.setPedido_venda_id(resultadoCartao.getPedidoVenda().getId());
            cartaoList.add(aux);
        }

        return cartaoList;
    }

    public CartaoResponseDom carregarCartaoById(Long id) {
        Optional<Cartao> resultado = cartaoRepository.findById(id);

        if (resultado.isPresent()) {
            Cartao cartao = resultado.get();
            CartaoResponseDom responseDOM = new CartaoResponseDom();
            responseDOM.setId(cartao.getId());
            responseDOM.setNumeroCartao(cartao.getNumeroCartao());
            responseDOM.setNomeTitular(cartao.getNomeTitular());
            responseDOM.setValidade(cartao.getValidade());
            responseDOM.setCvv(cartao.getCvv());
            responseDOM.setValor(cartao.getValor());
            responseDOM.setPedido_venda_id(cartao.getPedidoVenda().getId());
            return responseDOM;
        }
        return null;
    }

    public CartaoResponseDom criarCartao(CartaoRequestDom cartaoRequest) throws CrudException {
        List<String> mensagens = this.validarCartao(cartaoRequest);
        if (!mensagens.isEmpty()) {
            throw new CrudException(mensagens);
        }

        PedidoVenda pedidoVenda = pedidoVendaRepository.findById(cartaoRequest.getPedido_venda_id())
                .orElseThrow(() -> new CrudException(List.of("Pedido de venda não encontrado")));

        Cartao cartaoEntidade = new Cartao();
        cartaoEntidade.setNumeroCartao(cartaoRequest.getNumeroCartao());
        cartaoEntidade.setNomeTitular(cartaoRequest.getNomeTitular());
        cartaoEntidade.setValidade(cartaoRequest.getValidade());
        cartaoEntidade.setCvv(cartaoRequest.getCvv());
        cartaoEntidade.setValor(cartaoRequest.getValor());
        cartaoEntidade.setPedidoVenda(pedidoVenda);

        Cartao resultado = cartaoRepository.save(cartaoEntidade);

        CartaoResponseDom responseDOM = new CartaoResponseDom();
        responseDOM.setId(resultado.getId());
        responseDOM.setNumeroCartao(resultado.getNumeroCartao());
        responseDOM.setNomeTitular(resultado.getNomeTitular());
        responseDOM.setValidade(resultado.getValidade());
        responseDOM.setCvv(resultado.getCvv());
        responseDOM.setValor(resultado.getValor());
        responseDOM.setPedido_venda_id(resultado.getPedidoVenda().getId());

        return responseDOM;
    }

    public CartaoResponseDom atualizarCartao(Long id, CartaoRequestDom cartaoRequest) {
        Optional<Cartao> resultado = cartaoRepository.findById(id).map(record -> {
            PedidoVenda pedidoVenda = pedidoVendaRepository.findById(cartaoRequest.getPedido_venda_id())
                    .orElseThrow(() -> new RuntimeException("Pedido de venda não encontrado"));

            record.setNumeroCartao(cartaoRequest.getNumeroCartao());
            record.setNomeTitular(cartaoRequest.getNomeTitular());
            record.setValidade(cartaoRequest.getValidade());
            record.setCvv(cartaoRequest.getCvv());
            record.setValor(cartaoRequest.getValor());
            record.setPedidoVenda(pedidoVenda);

            return cartaoRepository.save(record);
        });

        if (resultado.isPresent()) {
            Cartao cartaoEntidade = resultado.get();

            CartaoResponseDom responseDOM = new CartaoResponseDom();
            responseDOM.setId(cartaoEntidade.getId());
            responseDOM.setNumeroCartao(cartaoEntidade.getNumeroCartao());
            responseDOM.setNomeTitular(cartaoEntidade.getNomeTitular());
            responseDOM.setValidade(cartaoEntidade.getValidade());
            responseDOM.setCvv(cartaoEntidade.getCvv());
            responseDOM.setValor(cartaoEntidade.getValor());
            responseDOM.setPedido_venda_id(cartaoEntidade.getPedidoVenda().getId());

            return responseDOM;
        }
        return null;
    }

    public void excluirCartao(Long id) {
        cartaoRepository.deleteById(id);
    }

    public List<String> validarCartao(CartaoRequestDom cartao) {
        List<String> mensagens = new ArrayList<>();

        if (cartao.getNumeroCartao() <= 0) {
            mensagens.add("Número do cartão inválido");
        }
        if (cartao.getNomeTitular() == null || cartao.getNomeTitular().isEmpty()) {
            mensagens.add("Nome do titular não informado");
        }
        LocalDate LocalDate = null;
        if (cartao.getValidade() == null || cartao.getValidade().isBefore(LocalDate.now())) {
            mensagens.add("Validade do cartão inválida");
        }
        if (cartao.getCvv() == null || cartao.getCvv().isEmpty()) {
            mensagens.add("CVV não informado");
        }
        if (cartao.getPedido_venda_id() == null) {
            mensagens.add("Pedido de venda não informado");
        }
        return mensagens;
    }


}
