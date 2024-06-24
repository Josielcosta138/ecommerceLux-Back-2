package br.com.ecommerceLux.controllers;


import br.com.ecommerceLux.entitys.PedidoVendaItem;
import br.com.ecommerceLux.useCases.PedidoVendaService;
import br.com.ecommerceLux.useCases.pedidoVenda.domains.PedidoVendaRequestDom;
import br.com.ecommerceLux.useCases.pedidoVenda.domains.PedidoVendaResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecommerce/pedidovenda")
public class PedidoVendaController {

    @Autowired
    private PedidoVendaService pedidoVendaService;

    @GetMapping("/carregar")
    public ResponseEntity<List<PedidoVendaResponseDom>>
        carregarPedidosVenda() {
            try {
                List<PedidoVendaResponseDom>
                        response = pedidoVendaService.carregarPedidosVenda();
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
    public ResponseEntity<PedidoVendaResponseDom>
        carregarPedidoVendaById(@PathVariable Long id) {
            try {
                PedidoVendaResponseDom
                        responseDOM = pedidoVendaService.carregarPedidoVendaById(id);

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
    public ResponseEntity<?>
        criarPedidoVenda(@RequestBody PedidoVendaRequestDom pedidoVenda) {
            try {
                PedidoVendaResponseDom responseDOM = pedidoVendaService.criarPedidoVenda(pedidoVenda);
                return ResponseEntity.status(201).body(responseDOM);

            } catch (CrudException es) {
                es.printStackTrace();
                return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));

            } catch (Exception e) {
                e.printStackTrace();

                return ResponseEntity.internalServerError().body(ResponseUtil.responseMap
                        ("Erro não mapeado: " + e.getMessage()));
            }
    }

}
