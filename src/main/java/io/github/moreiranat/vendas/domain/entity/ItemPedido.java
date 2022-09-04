package io.github.moreiranat.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne //muitos itens de pedido para 1 pedido
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne //muitos itens de pedido para 1 produto
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column
    private Integer quantidade;

}
