package io.github.moreiranat.vendas.rest.controller;

import io.github.moreiranat.vendas.domain.entity.Pedido;
import io.github.moreiranat.vendas.rest.dto.PedidoDTO;
import io.github.moreiranat.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO dto) { //Integer: vai retornar apenas o id do pedido gerado (codigo do pedido)
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
