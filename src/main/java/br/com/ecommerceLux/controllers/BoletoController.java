package br.com.ecommerceLux.controllers;

import br.com.ecommerceLux.useCases.BoletoService;
import br.com.ecommerceLux.useCases.boleto.domains.BoletoRequestDom;
import br.com.ecommerceLux.useCases.boleto.domains.BoletoResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecommerce/boletos")
public class BoletoController {
    @Autowired
    private BoletoService boletoService;

    @GetMapping("/carregar")
    public ResponseEntity<List<BoletoResponseDom>> carregarBoletos() {
        try {
            List<BoletoResponseDom> response = boletoService.carregarBoletos();

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
    public ResponseEntity<BoletoResponseDom> carregarBoletoById(@PathVariable Long id) {
        try {
            BoletoResponseDom responseDOM = boletoService.carregarBoletoById(id);
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
    public ResponseEntity<?> criarBoleto(@RequestBody BoletoRequestDom boleto) {
        try {
            BoletoResponseDom responseDOM = boletoService.criarBoleto(boleto);
            return ResponseEntity.status(201).body(responseDOM);
        } catch (CrudException es) {
            es.printStackTrace();
            return ResponseEntity.badRequest().body
                    (ResponseUtil.responseMap(es.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body
                    (ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<BoletoResponseDom>
        atualizarBoleto(@PathVariable Long id, @RequestBody BoletoRequestDom boleto) {
            try {
                BoletoResponseDom responseDOM = boletoService.atualizarBoleto(id, boleto);

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
    public ResponseEntity<Void> excluirBoleto(@PathVariable Long id) {
        try {
            boletoService.excluirBoleto(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }




}
