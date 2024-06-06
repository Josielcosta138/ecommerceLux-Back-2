    package br.com.ecommerceLux.controllers;

import br.com.ecommerceLux.useCases.ClienteService;
import br.com.ecommerceLux.useCases.clientes.domains.ClientesResponseDom;
import br.com.ecommerceLux.utils.CrudException;
import br.com.ecommerceLux.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/ecommerce/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/carregar")
    public ResponseEntity<List<ClientesResponseDom>> carregarClientes() {
        try {
            List<ClientesResponseDom> response = clienteService.carregarClientes();

            int status = 200;
            if (response.isEmpty()){
                status = 204;
            }
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/carregar/{id}")
    public  ResponseEntity<ClientesResponseDom> carregarClientesById(@PathVariable Long id){
        try {
            ClientesResponseDom responseDOM = clienteService.carregarClienteById(id);
            if (responseDOM != null){
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<?> criarCliente(@RequestBody ClientesResponseDom cliente){
        try {
            ClientesResponseDom responseDOM = clienteService.criarClientes(cliente);
            return ResponseEntity.status(201).body(responseDOM);
        }
        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado"+ e.getMessage()));
        }
    }



    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<ClientesResponseDom> atualizarCliente(@PathVariable Long id, @RequestBody ClientesResponseDom cliente){
        try {
            ClientesResponseDom responseDOM = clienteService.atualizarCliente(id, cliente);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }



    @DeleteMapping("/excluirCliente/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        try {
            clienteService.excluirCliente(id);
            return ResponseEntity.ok(null);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
