package io.github.moreiranat.vendas.domain.repository;

import io.github.moreiranat.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
