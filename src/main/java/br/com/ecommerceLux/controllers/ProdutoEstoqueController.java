package br.com.ecommerceLux.controllers;


import br.com.ecommerceLux.useCases.ProdutoEstoqueService;
import br.com.ecommerceLux.useCases.ProdutoService;
import br.com.ecommerceLux.useCases.produtoEstoque.domains.ProdutoEstoque.ProdutoEstoqueRequestDom;
import br.com.ecommerceLux.useCases.produtoEstoque.domains.ProdutoEstoque.ProdutoEstoqueResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ecommerce/produtosestoque")
public class ProdutoEstoqueController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ProdutoEstoqueService produtoEstoqueService;

    @GetMapping("/carregar")
    public ResponseEntity<List<ProdutoEstoqueResponseDom>> carregarProdutosEstoque(){
        try {
            List<ProdutoEstoqueResponseDom> responseDoms = produtoEstoqueService.carregarProdutoSestoque();

            int status = 200;

            if (responseDoms.isEmpty()) {
                status = 204;
            }
            return ResponseEntity
                    .status(status)
                    .body(responseDoms);
        }

        catch (Exception e){
            e.printStackTrace();

            return ResponseEntity
                        .badRequest()
                        .body(null);
        }
    }


    @GetMapping("/carregar/{id}")
    public ResponseEntity<ProdutoEstoqueResponseDom> carregarProdutoEstoqueById(@PathVariable Long id){
        try {
            ProdutoEstoqueResponseDom responseDom = produtoEstoqueService.carregarProdutosEstoqueById(id);

            if (responseDom != null){
                return ResponseEntity.ok(responseDom);
            }
            return ResponseEntity
                        .status(204)
                        .body(null);
        }
        catch (Exception e){
            e.printStackTrace();

            return ResponseEntity
                        .badRequest()
                        .body(null);
        }
    }


    @CrossOrigin
    @PostMapping("/criar")
    public ResponseEntity<?> criarProtudoEstoque(@RequestBody ProdutoEstoqueRequestDom produtoEstoqueRequestDom){

        try {
            ProdutoEstoqueResponseDom responseDom = produtoEstoqueService.criarProdutoEstoque(produtoEstoqueRequestDom);

            return ResponseEntity
                    .status(201)
                    .body(responseDom);
        }

        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.responseMap(es.getMessage()));
        }

        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMap("Erro não mapeado" + e.getMessage()));
        }
    }


}
