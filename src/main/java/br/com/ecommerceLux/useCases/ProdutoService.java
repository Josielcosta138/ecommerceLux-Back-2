package br.com.ecommerceLux.useCases;

import br.com.ecommerceLux.entitys.Categoria;
import br.com.ecommerceLux.entitys.Produto;
import br.com.ecommerceLux.entitys.ProdutoEstoque;
import br.com.ecommerceLux.repositorys.ProdutoEstoqueRepository;
import br.com.ecommerceLux.repositorys.ProdutoRepository;
import br.com.ecommerceLux.useCases.endereco.domains.EnderecoRequestDom;
import br.com.ecommerceLux.useCases.produto.domains.ProdutoRequestDom;
import br.com.ecommerceLux.useCases.produto.domains.ProdutoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;


    public List<ProdutoResponseDom> carregarProdutos() {

        List<Produto> listaDeProdutos = produtoRepository.findAll();
        List<ProdutoResponseDom> produtosDom = new ArrayList<>();

        for (Produto resultadoProdDom : listaDeProdutos){

            ProdutoResponseDom aux = new ProdutoResponseDom();
            aux.setId(resultadoProdDom.getId());
            aux.setNome(resultadoProdDom.getNome());
            aux.setPreco(resultadoProdDom.getPreco());
            aux.setCategoria(resultadoProdDom.getCategoria());
            produtosDom.add(aux);

        }
        return produtosDom;
    }


public List<ProdutoResponseDom> carregarProdutosCategoria() {

    List<Produto> listaDeProdutos = produtoRepository.findAll();
    List<ProdutoResponseDom> produtosDom = new ArrayList<>();

    for (Produto resultadoProdDom : listaDeProdutos){

        if (resultadoProdDom.getCategoria().equals(Categoria.ACESSORIOS)){
            ProdutoResponseDom aux = new ProdutoResponseDom();
            aux.setCodigo(resultadoProdDom.getCodigo());
            aux.setId(resultadoProdDom.getId());
            aux.setNome(resultadoProdDom.getNome());
            aux.setPreco(resultadoProdDom.getPreco());
            aux.setCategoria(resultadoProdDom.getCategoria());
            aux.setTamanho(resultadoProdDom.getTamanho());
            aux.setEnderecoImagem(resultadoProdDom.getEnderecoImagem());
            produtosDom.add(aux);
        }

    }
    return produtosDom;
}


    public ProdutoResponseDom carregarProdutosById(Long id) {
        Optional<Produto> resultadoProduto = produtoRepository.findById(id);

        if (resultadoProduto.isPresent()){

            Produto produto = resultadoProduto.get();
            ProdutoResponseDom responseDom = new ProdutoResponseDom();

            responseDom.setId(produto.getId());
            responseDom.setCodigo(produto.getCodigo());
            responseDom.setNome(produto.getNome());
            responseDom.setCategoria(produto.getCategoria());
            responseDom.setPreco(produto.getPreco());
            responseDom.setTamanho(produto.getTamanho());
            responseDom.setDescricao(produto.getDescricao());
            responseDom.setEnderecoImagem(produto.getEnderecoImagem());

            return responseDom;

        }

        return null;
    }

    public ProdutoResponseDom criarProduto(ProdutoRequestDom produto) throws CrudException {
        List<String> mensagemErro = this.validarProduto(produto);

        if (!mensagemErro.isEmpty()){
            throw new CrudException(mensagemErro);
        }

        Produto produtoEntidade = new Produto();
        produtoEntidade.setNome(produto.getNome());
        produtoEntidade.setCategoria(produto.getCategoria());
        produtoEntidade.setPreco(produto.getPreco());

            Produto resultadoResponse = produtoRepository.save(produtoEntidade);

        ProdutoResponseDom responseDom = new ProdutoResponseDom();
        responseDom.setId(resultadoResponse.getId());
        responseDom.setNome(resultadoResponse.getNome());
        responseDom.setCategoria(resultadoResponse.getCategoria());
        responseDom.setPreco(resultadoResponse.getPreco());

        return responseDom;
    }




    private List<String> validarProduto(ProdutoRequestDom produto) {
        List<String> mensagens = new ArrayList<>();

        if (produto.getNome() == null || produto.getNome().equals("")) {
            mensagens.add("nome não informado");
        }
        if (produto.getCategoria() == null) {
            mensagens.add("categoria não informado");
        }
        if (produto.getPreco() == null) {
            mensagens.add("preço não informado");
        }
        return mensagens;

    }

    public ProdutoResponseDom atualizarProduto(Long id, ProdutoResponseDom produto) {
        Optional<Produto> resultado = produtoRepository.findById(id).map(record -> {

            record.setNome(produto.getNome());
            record.setPreco(produto.getPreco());
            record.setCategoria(produto.getCategoria());
            produtoRepository.save(record);

            return record;
        });

        if (resultado.isPresent()) {
            Produto produtoEntidade = resultado.get();

            ProdutoResponseDom responseDom = new  ProdutoResponseDom();

            responseDom.setId(produtoEntidade.getId());
            responseDom.setNome(produtoEntidade.getNome());
            responseDom.setPreco(produtoEntidade.getPreco());
            responseDom.setCategoria(produtoEntidade.getCategoria());

            return responseDom;
        }

        return null;
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
