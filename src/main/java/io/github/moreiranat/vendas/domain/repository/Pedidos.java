package io.github.moreiranat.vendas.domain.repository;

import io.github.moreiranat.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
