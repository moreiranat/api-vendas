package io.github.moreiranat.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO { //DTO: Data Transfer Object - padrao que serve para mapear um objeto com propriedades simples

    private Integer cliente; //id do cliente
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
