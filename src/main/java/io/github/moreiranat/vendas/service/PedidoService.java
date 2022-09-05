package io.github.moreiranat.vendas.service;

import io.github.moreiranat.vendas.domain.entity.Pedido;
import io.github.moreiranat.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
