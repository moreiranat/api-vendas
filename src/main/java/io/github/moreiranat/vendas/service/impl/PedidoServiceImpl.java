package io.github.moreiranat.vendas.service.impl;

import io.github.moreiranat.vendas.domain.entity.Cliente;
import io.github.moreiranat.vendas.domain.entity.ItemPedido;
import io.github.moreiranat.vendas.domain.entity.Pedido;
import io.github.moreiranat.vendas.domain.entity.Produto;
import io.github.moreiranat.vendas.domain.enums.StatusPedido;
import io.github.moreiranat.vendas.domain.repository.ClienteRepository;
import io.github.moreiranat.vendas.domain.repository.ItemPedidoRepository;
import io.github.moreiranat.vendas.domain.repository.PedidoRepository;
import io.github.moreiranat.vendas.domain.repository.ProdutoRepository;
import io.github.moreiranat.vendas.exception.PedidoNaoEncontradoException;
import io.github.moreiranat.vendas.exception.RegraNegocioException;
import io.github.moreiranat.vendas.rest.dto.ItemPedidoDTO;
import io.github.moreiranat.vendas.rest.dto.PedidoDTO;
import io.github.moreiranat.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //gera um construtor com todos os argumentos obrigatorios (os que tem final - tem que ser instanciado no momento da criacao da classe)
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());

    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
