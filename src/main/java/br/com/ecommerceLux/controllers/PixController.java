package br.com.ecommerceLux.controllers;

import br.com.ecommerceLux.useCases.PixService;
import br.com.ecommerceLux.useCases.pix.domains.PixRequestDom;
import br.com.ecommerceLux.useCases.pix.domains.PixResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecommerce/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @GetMapping("/carregar")
    public ResponseEntity<List<PixResponseDom>> carregarPix() {
        try {
            List<PixResponseDom> response = pixService.carregarPix();

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
    public ResponseEntity<PixResponseDom> carregarPixById(@PathVariable Long id) {
        try {
            PixResponseDom responseDOM = pixService.carregarPixById(id);
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
    public ResponseEntity<?> criarPix(@RequestBody PixRequestDom pix) {
        try {
            PixResponseDom responseDOM = pixService.criarPix(pix);
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
    public ResponseEntity<PixResponseDom> atualizarPix(@PathVariable Long id, @RequestBody PixRequestDom pix) {
        try {
            PixResponseDom responseDOM = pixService.atualizarPix(id, pix);

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
    public ResponseEntity<Void> excluirPix(@PathVariable Long id) {
        try {
            pixService.excluirPix(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



}
