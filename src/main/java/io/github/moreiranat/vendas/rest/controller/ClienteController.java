package io.github.moreiranat.vendas.rest.controller;

import io.github.moreiranat.vendas.domain.entity.Cliente;
import io.github.moreiranat.vendas.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()) {
            return  ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) { //só recebe parâmetros via URL, não recebe no corpo da requisição
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build(); //nao retorna nada no corpo de resposta da requisicao, só o codigo de status mesmo
        }

        return ResponseEntity.notFound().build();
    }
}
