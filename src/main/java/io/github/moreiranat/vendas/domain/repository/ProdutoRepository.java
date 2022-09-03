package io.github.moreiranat.vendas.domain.repository;

import io.github.moreiranat.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
