package io.github.moreiranat.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO { //DTO: Data Transfer Object - padrao que serve para mapear um objeto com propriedades simples

    private Integer cliente; //id do cliente
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
