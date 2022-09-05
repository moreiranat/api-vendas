package io.github.moreiranat.vendas.rest.dto;

import io.github.moreiranat.vendas.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO { //DTO: Data Transfer Object - padrao que serve para mapear um objeto com propriedades simples

    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente; //id do cliente

    @NotNull(message = "Campo total do pedido é obrigatório.")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;
}
