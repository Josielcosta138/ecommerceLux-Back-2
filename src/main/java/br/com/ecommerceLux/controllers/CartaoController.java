package br.com.ecommerceLux.controllers;


import br.com.ecommerceLux.useCases.CartaoService;
import br.com.ecommerceLux.useCases.cartao.domains.CartaoRequestDom;
import br.com.ecommerceLux.useCases.cartao.domains.CartaoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecommerce/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping("/carregar")
    public ResponseEntity<List<CartaoResponseDom>> carregarCartoes() {
        try {
            List<CartaoResponseDom> response = cartaoService.carregarCartoes();

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
    public ResponseEntity<CartaoResponseDom> carregarCartaoById(@PathVariable Long id) {
        try {
            CartaoResponseDom responseDOM = cartaoService.carregarCartaoById(id);
            if (responseDOM != null) {
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarCartao(@RequestBody CartaoRequestDom cartao) {
        try {
            CartaoResponseDom responseDOM = cartaoService.criarCartao(cartao);
            return ResponseEntity.status(201).body(responseDOM);
        } catch (CrudException es) {
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CartaoResponseDom> atualizarCartao(@PathVariable Long id, @RequestBody CartaoRequestDom cartao) {
        try {
            CartaoResponseDom responseDOM = cartaoService.atualizarCartao(id, cartao);

            if (responseDOM == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCartao(@PathVariable Long id) {
        try {
            cartaoService.excluirCartao(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
