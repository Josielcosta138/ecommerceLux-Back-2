package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.Produto;
import br.com.ecommerceLux.entitys.ProdutoEstoque;
import br.com.ecommerceLux.repositorys.ProdutoEstoqueRepository;
import br.com.ecommerceLux.repositorys.ProdutoEstoqueUnicoRepository;
import br.com.ecommerceLux.repositorys.ProdutoRepository;
import br.com.ecommerceLux.useCases.produtoEstoque.domains.ProdutoEstoque.ProdutoEstoqueRequestDom;
import br.com.ecommerceLux.useCases.produtoEstoque.domains.ProdutoEstoque.ProdutoEstoqueResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoEstoqueService {

    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;
    @Autowired
    private ProdutoEstoqueUnicoRepository produtoEstoqueUnicoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    public List<ProdutoEstoqueResponseDom> carregarProdutoSestoque() {
        List<ProdutoEstoque> resultadoEnderecoDom = produtoEstoqueRepository.findAll();

        List<ProdutoEstoqueResponseDom> listaDeProdutos = new ArrayList<>();

            for (ProdutoEstoque produtoEstoque: resultadoEnderecoDom){

                ProdutoEstoqueResponseDom aux = new ProdutoEstoqueResponseDom();
                aux.setId(produtoEstoque.getId());
                aux.setLocalizacao(produtoEstoque.getLocalizacao());
                aux.setQuantidade(produtoEstoque.getQuantidade());
                aux.setProduto_id(produtoEstoque.getProduto());

                listaDeProdutos.add(aux);
            }

        return listaDeProdutos;
    }


    // -- Carregar produto estoque por id do produto
    public ProdutoEstoqueResponseDom carregarProdutosEstoqueByIdProduto(Long idProduto) {

        Optional<ProdutoEstoque> resultadoProdutosEstoque = produtoEstoqueUnicoRepository.findByProdutoId(idProduto);

        if (resultadoProdutosEstoque.isPresent()) {
            ProdutoEstoque produtoEstoque = resultadoProdutosEstoque.get();

            ProdutoEstoqueResponseDom responseDom = new ProdutoEstoqueResponseDom();
            responseDom.setId(produtoEstoque.getId());
            responseDom.setQuantidade(produtoEstoque.getQuantidade());
            responseDom.setLocalizacao(produtoEstoque.getLocalizacao());
            responseDom.setProduto_id(produtoEstoque.getProduto());

            return responseDom;
        }

        return null;
    }


    public ProdutoEstoqueResponseDom carregarProdutosEstoqueById(Long id) {

        Optional<ProdutoEstoque> resultado = produtoEstoqueRepository.findById(id);

        if (resultado.isPresent()){
            ProdutoEstoque produtoEstoque = resultado.get();

            ProdutoEstoqueResponseDom responseDom = new ProdutoEstoqueResponseDom();
            responseDom.setId(produtoEstoque.getId());
            responseDom.setLocalizacao(produtoEstoque.getLocalizacao());
            responseDom.setQuantidade(produtoEstoque.getQuantidade());
            responseDom.setProduto_id(produtoEstoque.getProduto());

            return responseDom;

        }
        return null;

    }






    public ProdutoEstoqueResponseDom criarProdutoEstoque(ProdutoEstoqueRequestDom produtoEstoque) throws CrudException {
        List<String> mensagens = this.validarProdutoEstoque(produtoEstoque);

            if (!mensagens.isEmpty()){
                throw new CrudException(mensagens);
            }

//            Optional<Produto> resultadoProdutoId = produtoRepository.findById(produtoEstoque.);
            //Validação para criar 5 produtos por almoxarifado

            ProdutoEstoque produtoEstoqueEntidades = new ProdutoEstoque();
                produtoEstoqueEntidades.setLocalizacao(produtoEstoque.getLocalizacao());
                produtoEstoqueEntidades.setQuantidade(produtoEstoque.getQuantidade());
                produtoEstoqueEntidades.setProduto(produtoEstoque.getProduto_id());

                ProdutoEstoque resultado = produtoEstoqueRepository.save(produtoEstoqueEntidades);

            ProdutoEstoqueResponseDom responseDom = new ProdutoEstoqueResponseDom();
                responseDom.setId(resultado.getId());
                responseDom.setLocalizacao(resultado.getLocalizacao());
                responseDom.setQuantidade(resultado.getQuantidade());
                responseDom.setProduto_id(resultado.getProduto());

        return responseDom;
    }


    private List<String> validarProdutoEstoque(ProdutoEstoqueRequestDom produtoEstoqueRequestDom) {
        List<String> mensagens = new ArrayList<>();

        if (produtoEstoqueRequestDom.getLocalizacao() == null || produtoEstoqueRequestDom.getLocalizacao().equals("")) {
            mensagens.add("localização não informado");
        }
        if (produtoEstoqueRequestDom.getQuantidade() < 0 ) {
            mensagens.add("Quantidade negativa");
        }
        if (produtoEstoqueRequestDom.getProduto_id() == null || produtoEstoqueRequestDom.getProduto_id().equals("")) {
            mensagens.add("Produto não informado");
        }
        return mensagens;

    }

}
