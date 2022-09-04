package io.github.moreiranat.vendas.service.impl;

import io.github.moreiranat.vendas.domain.repository.PedidoRepository;
import io.github.moreiranat.vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }
}
