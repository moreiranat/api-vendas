package io.github.moreiranat.vendas.service;

import io.github.moreiranat.vendas.domain.entity.Pedido;
import io.github.moreiranat.vendas.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
