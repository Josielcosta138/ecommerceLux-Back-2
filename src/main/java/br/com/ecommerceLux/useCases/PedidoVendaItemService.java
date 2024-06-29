package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.PedidoVenda;
import br.com.ecommerceLux.entitys.PedidoVendaItem;
import br.com.ecommerceLux.entitys.Produto;
import br.com.ecommerceLux.entitys.ProdutoEstoque;
import br.com.ecommerceLux.repositorys.PedidoVendaItemRepository;
import br.com.ecommerceLux.repositorys.PedidoVendaRepository;
import br.com.ecommerceLux.repositorys.ProdutoEstoqueRepository;
import br.com.ecommerceLux.repositorys.ProdutoRepository;
import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemRequestDom;
import br.com.ecommerceLux.useCases.pedidoVendaItem.domains.PedidoVendaItemResponseDom;
import br.com.ecommerceLux.useCases.produto.domains.ProdutoRequestDom;
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
public class PedidoVendaItemService {

    @Autowired
    private PedidoVendaItemRepository pedidoVendaItemRepository;
    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;



    public List<PedidoVendaItemResponseDom> criarListaPedidoVendaItens(List<PedidoVendaItemRequestDom> itensRequest, Long pedidoVendaId) {
        List<PedidoVendaItemResponseDom> itensResponse = new ArrayList<>();

        for (PedidoVendaItemRequestDom itemRequest : itensRequest) {
            PedidoVendaItemResponseDom itemResponse = new PedidoVendaItemResponseDom();

            // Buscar o Produto pelo ID
            Optional<Produto> produtoOptional = produtoRepository.findById(itemRequest.getProduto().getId());
            if (!produtoOptional.isPresent()) {
                throw new IllegalArgumentException("Produto não encontrado para o ID: " + itemRequest.getProduto().getId());
            }


            Produto produto = produtoOptional.get();

            ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findByProdutoId(produto.getId());
                if (produtoEstoque == null) {
                    throw new IllegalArgumentException("Estoque não encontrado para o produto ID: " + produto.getId());
                }

                //Baixa no estoque
                if (produtoEstoque.getQuantidade() < itemRequest.getQuantidade()) {
                    throw new IllegalArgumentException("Quantidade insuficiente no estoque para o produto ID: " + produto.getId());
                }


            produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - itemRequest.getQuantidade());
            produtoEstoqueRepository.save(produtoEstoque);


            // Response
            itemResponse.setPedidoVendaId(pedidoVendaId);
            itemResponse.setProduto(produtoOptional.get());
            itemResponse.setQuantidade(itemRequest.getQuantidade());
            itemResponse.setPreco(itemRequest.getPreco());

            // Salvar o item do pedido no banco de dados
            PedidoVendaItem itemEntidade = new PedidoVendaItem();
            itemEntidade.setPedidoVenda(pedidoVendaRepository.getOne(pedidoVendaId)); // Associando o pedido de venda
            itemEntidade.setProduto(produtoOptional.get());
            itemEntidade.setQuantidade(itemRequest.getQuantidade());
            itemEntidade.setPreco(itemRequest.getPreco());

            pedidoVendaItemRepository.save(itemEntidade);



            itensResponse.add(itemResponse);
        }


        return itensResponse;
    }






    public List<String> validarPedidoVendaItem(PedidoVendaItemRequestDom item) {
        List<String> mensagens = new ArrayList<>();

        if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
            mensagens.add("Quantidade inválida");
        }
        if (item.getPreco() == null || item.getPreco() <= 0) {
            mensagens.add("Preço inválido");
        }
        if (item.getPedidoVenda().getId() == null) {
            mensagens.add("Pedido de Venda não informado");
        }
        if (item.getProduto().getId() == null) {
            mensagens.add("Produto não informado");
        }
        return mensagens;
    }


}
