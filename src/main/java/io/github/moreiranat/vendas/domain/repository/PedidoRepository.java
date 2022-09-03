package io.github.moreiranat.vendas.domain.repository;

import io.github.moreiranat.vendas.domain.entity.Cliente;
import io.github.moreiranat.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
