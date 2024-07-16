package br.com.ecommerceLux.controllers;

import br.com.ecommerceLux.useCases.ProdutoService;
import br.com.ecommerceLux.useCases.produto.domains.ProdutoRequestDom;
import br.com.ecommerceLux.useCases.produto.domains.ProdutoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecommerce/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping("/carregar")
    public ResponseEntity<List<ProdutoResponseDom>> carregarProdutos() {

        try {
            List<ProdutoResponseDom> response = produtoService.carregarProdutos();

            int status = 200;
            if (response.isEmpty()) {
                status = 204;
            }
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/carregar/categoriacombo")
    public ResponseEntity<List<ProdutoResponseDom>> carregarProdutosCategoria() {

        try {
            List<ProdutoResponseDom> response = produtoService.carregarProdutosCategoria();

            int status = 200;
            if (response.isEmpty()) {
                status = 204;
            }
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }




    @GetMapping("/carregar/todascategoria/{id}")
    public ResponseEntity<List<ProdutoResponseDom>> carregarProdutosTodasCategoria(@PathVariable Long id) {

        try {
            List<ProdutoResponseDom> response = produtoService.carregarProdutosTodasCategoria(id);

            int status = 200;
            if (response.isEmpty()) {
                status = 204;
            }
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }




    @GetMapping("/carregar/filtrarprodutos/{produtoNome}")
    public ResponseEntity<List<ProdutoResponseDom>> carregarProdutosPorNome(@PathVariable String produtoNome) {

        try {
            List<ProdutoResponseDom> response = produtoService.carregarProdutosPorNome(produtoNome);

            int status = 200;
            if (response.isEmpty()) {
                status = 204;
            }
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }





    @GetMapping("/carregar/{id}")
    public ResponseEntity<ProdutoResponseDom> carregarProdutosPorId(@PathVariable Long id){
        try {
            ProdutoResponseDom responseDom = produtoService.carregarProdutosById(id);
            if (responseDom !=null){
                return ResponseEntity.ok(responseDom);
            }
            return ResponseEntity.status(204).body(null);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @CrossOrigin
    @PostMapping("/criar")
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoRequestDom produto){
        try{
            ProdutoResponseDom responseDom = produtoService.criarProduto(produto);
            return ResponseEntity.status(201).body(responseDom);
        }
        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado"+ e.getMessage()));
        }
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoResponseDom> atualizarProdtu(@PathVariable Long id, @RequestBody ProdutoResponseDom produto){
        try {
            ProdutoResponseDom responseDom = produtoService.atualizarProduto(id, produto);
            if (responseDom == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDom);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.ok(null);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
