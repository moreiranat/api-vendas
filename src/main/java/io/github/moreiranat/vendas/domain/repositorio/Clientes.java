package io.github.moreiranat.vendas.domain.repositorio;

import io.github.moreiranat.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> { //são todos os Clientes

    List<Cliente> findByNomeLike(String nome);

}
