package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.PedidoVenda;
import br.com.ecommerceLux.entitys.Pix;
import br.com.ecommerceLux.repositorys.PedidoVendaRepository;
import br.com.ecommerceLux.repositorys.PixRepository;
import br.com.ecommerceLux.useCases.pix.domains.PixRequestDom;
import br.com.ecommerceLux.useCases.pix.domains.PixResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PixService {
    @Autowired
    private PixRepository pixRepository;
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    public List<PixResponseDom> carregarPix() {
        List<Pix> listaDePix = pixRepository.findAll();
        List<PixResponseDom> pixList = new ArrayList<>();

        for (Pix resultadoPix : listaDePix) {
            PixResponseDom aux = new PixResponseDom();
            aux.setId(resultadoPix.getId());
            aux.setChavePix(resultadoPix.getChavePix());
            aux.setDataTransacao(resultadoPix.getDataTransacao());
            aux.setValorTransacao(resultadoPix.getValorTransacao());
            aux.setPedido_venda_id(resultadoPix.getPedidoVenda().getId());
            pixList.add(aux);
        }

        return pixList;
    }

    public PixResponseDom carregarPixById(Long id) {
        Optional<Pix> resultado = pixRepository.findById(id);

        if (resultado.isPresent()) {
            Pix pix = resultado.get();
            PixResponseDom responseDOM = new PixResponseDom();
            responseDOM.setId(pix.getId());
            responseDOM.setChavePix(pix.getChavePix());
            responseDOM.setDataTransacao(pix.getDataTransacao());
            responseDOM.setValorTransacao(pix.getValorTransacao());
            responseDOM.setPedido_venda_id(pix.getPedidoVenda().getId());
            return responseDOM;
        }
        return null;
    }

    public PixResponseDom criarPix(PixRequestDom pixRequest) throws CrudException {
        List<String> mensagens = this.validarPix(pixRequest);
        if (!mensagens.isEmpty()) {
            throw new CrudException(mensagens);
        }

        PedidoVenda pedidoVenda = pedidoVendaRepository.findById(pixRequest.getPedido_venda_id())
                .orElseThrow(() -> new CrudException(List.of("Pedido de venda não encontrado")));

        Pix pixEntidade = new Pix();
        pixEntidade.setChavePix(pixRequest.getChavePix());
        pixEntidade.setDataTransacao(pixRequest.getDataTransacao());
        pixEntidade.setValorTransacao(pixRequest.getValorTransacao());
        pixEntidade.setPedidoVenda(pedidoVenda);

        Pix resultado = pixRepository.save(pixEntidade);

        PixResponseDom responseDOM = new PixResponseDom();
        responseDOM.setId(resultado.getId());
        responseDOM.setChavePix(resultado.getChavePix());
        responseDOM.setDataTransacao(resultado.getDataTransacao());
        responseDOM.setValorTransacao(resultado.getValorTransacao());
        responseDOM.setPedido_venda_id(resultado.getPedidoVenda().getId());

        return responseDOM;
    }

    public PixResponseDom atualizarPix(Long id, PixRequestDom pixRequest) {
        Optional<Pix> resultado = pixRepository.findById(id).map(record -> {
            PedidoVenda pedidoVenda = pedidoVendaRepository.findById(pixRequest.getPedido_venda_id())
                    .orElseThrow(() -> new RuntimeException("Pedido de venda não encontrado"));

            record.setChavePix(pixRequest.getChavePix());
            record.setDataTransacao(pixRequest.getDataTransacao());
            record.setValorTransacao(pixRequest.getValorTransacao());
            record.setPedidoVenda(pedidoVenda);

            return pixRepository.save(record);
        });

        if (resultado.isPresent()) {
            Pix pixEntidade = resultado.get();

            PixResponseDom responseDOM = new PixResponseDom();
            responseDOM.setId(pixEntidade.getId());
            responseDOM.setChavePix(pixEntidade.getChavePix());
            responseDOM.setDataTransacao(pixEntidade.getDataTransacao());
            responseDOM.setValorTransacao(pixEntidade.getValorTransacao());
            responseDOM.setPedido_venda_id(pixEntidade.getPedidoVenda().getId());

            return responseDOM;
        }
        return null;
    }

    public void excluirPix(Long id) {
        pixRepository.deleteById(id);
    }

    public List<String> validarPix(PixRequestDom pix) {
        List<String> mensagens = new ArrayList<>();

        if (pix.getChavePix() == null || pix.getChavePix().isEmpty()) {
            mensagens.add("Chave Pix não informada");
        }
        if (pix.getDataTransacao() == null) {
            mensagens.add("Data de transação não informada");
        }
        if (pix.getValorTransacao() == null || pix.getValorTransacao().compareTo(BigDecimal.ZERO) <= 0) {
            mensagens.add("Valor da transação inválido");
        }
        if (pix.getPedido_venda_id() == null) {
            mensagens.add("Pedido de venda não informado");
        }
        return mensagens;
    }


}
