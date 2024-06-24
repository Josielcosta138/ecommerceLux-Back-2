package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.Boleto;
import br.com.ecommerceLux.entitys.PedidoVenda;
import br.com.ecommerceLux.repositorys.BoletoRepository;
import br.com.ecommerceLux.repositorys.PedidoVendaRepository;
import br.com.ecommerceLux.useCases.boleto.domains.BoletoRequestDom;
import br.com.ecommerceLux.useCases.boleto.domains.BoletoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    public List<BoletoResponseDom> carregarBoletos() {
        List<Boleto> listaDeBoletos = boletoRepository.findAll();
        List<BoletoResponseDom> boletos = new ArrayList<>();

        for (Boleto resultadoBoleto : listaDeBoletos) {
            BoletoResponseDom aux = new BoletoResponseDom();
            aux.setId(resultadoBoleto.getId());
            aux.setCodigoBarras(resultadoBoleto.getCodigoBarras());
            aux.setDataVencimento(resultadoBoleto.getDataVencimento());
            aux.setValor(resultadoBoleto.getValor());
            aux.setPedido_venda_id(resultadoBoleto.getPedidoVenda().getId());
            boletos.add(aux);
        }

        return boletos;
    }

    public BoletoResponseDom carregarBoletoById(Long id) {
        Optional<Boleto> resultado = boletoRepository.findById(id);

        if (resultado.isPresent()) {
            Boleto boleto = resultado.get();
            BoletoResponseDom responseDOM = new BoletoResponseDom();
            responseDOM.setId(boleto.getId());
            responseDOM.setCodigoBarras(boleto.getCodigoBarras());
            responseDOM.setDataVencimento(boleto.getDataVencimento());
            responseDOM.setValor(boleto.getValor());
            responseDOM.setPedido_venda_id(boleto.getPedidoVenda().getId());
            return responseDOM;
        }
        return null;
    }

    public BoletoResponseDom criarBoleto(BoletoRequestDom boletoRequest) throws CrudException {
        List<String> mensagens = this.validarBoleto(boletoRequest);
        if (!mensagens.isEmpty()) {
            throw new CrudException(mensagens);
        }

        PedidoVenda pedidoVenda = pedidoVendaRepository.findById(boletoRequest.getPedido_venda_id())
                .orElseThrow(() -> new CrudException(List.of("Pedido de venda não encontrado")));

        Boleto boletoEntidade = new Boleto();
        boletoEntidade.setCodigoBarras(boletoRequest.getCodigoBarras());
        boletoEntidade.setDataVencimento(boletoRequest.getDataVencimento());
        boletoEntidade.setValor(boletoRequest.getValor());
        boletoEntidade.setPedidoVenda(pedidoVenda);

        Boleto resultado = boletoRepository.save(boletoEntidade);

        BoletoResponseDom responseDOM = new BoletoResponseDom();
        responseDOM.setId(resultado.getId());
        responseDOM.setCodigoBarras(resultado.getCodigoBarras());
        responseDOM.setDataVencimento(resultado.getDataVencimento());
        responseDOM.setValor(resultado.getValor());
        responseDOM.setPedido_venda_id(resultado.getPedidoVenda().getId());

        return responseDOM;
    }

    public BoletoResponseDom atualizarBoleto(Long id, BoletoRequestDom boletoRequest) {
        Optional<Boleto> resultado = boletoRepository.findById(id).map(record -> {
            PedidoVenda pedidoVenda = pedidoVendaRepository.findById(boletoRequest.getPedido_venda_id())
                    .orElseThrow(() -> new RuntimeException("Pedido de venda não encontrado"));

            record.setCodigoBarras(boletoRequest.getCodigoBarras());
            record.setDataVencimento(boletoRequest.getDataVencimento());
            record.setValor(boletoRequest.getValor());
            record.setPedidoVenda(pedidoVenda);

            return boletoRepository.save(record);
        });

        if (resultado.isPresent()) {
            Boleto boletoEntidade = resultado.get();

            BoletoResponseDom responseDOM = new BoletoResponseDom();
            responseDOM.setId(boletoEntidade.getId());
            responseDOM.setCodigoBarras(boletoEntidade.getCodigoBarras());
            responseDOM.setDataVencimento(boletoEntidade.getDataVencimento());
            responseDOM.setValor(boletoEntidade.getValor());
            responseDOM.setPedido_venda_id(boletoEntidade.getPedidoVenda().getId());

            return responseDOM;
        }
        return null;
    }

    public void excluirBoleto(Long id) {
        boletoRepository.deleteById(id);
    }

    public List<String> validarBoleto(BoletoRequestDom boleto) {
        List<String> mensagens = new ArrayList<>();

        if (boleto.getCodigoBarras() == null || boleto.getCodigoBarras().isEmpty()) {
            mensagens.add("Código de barras não informado");
        }
        if (boleto.getDataVencimento() == null) {
            mensagens.add("Data de vencimento não informada");
        }
        if (boleto.getValor() == null || boleto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            mensagens.add("Valor do boleto inválido");
        }
        if (boleto.getPedido_venda_id() == null) {
            mensagens.add("Pedido de venda não informado");
        }
        return mensagens;
    }

}
