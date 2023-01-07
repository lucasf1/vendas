package io.github.lucasf1.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.lucasf1.vendas.api.dto.ItemPedidoDTO;
import io.github.lucasf1.vendas.api.dto.PedidoDTO;
import io.github.lucasf1.vendas.domain.entity.Cliente;
import io.github.lucasf1.vendas.domain.entity.ItemPedido;
import io.github.lucasf1.vendas.domain.entity.Pedido;
import io.github.lucasf1.vendas.domain.entity.Produto;
import io.github.lucasf1.vendas.domain.repository.ClienteRepository;
import io.github.lucasf1.vendas.domain.repository.ItemPedidoRepository;
import io.github.lucasf1.vendas.domain.repository.PedidoRepository;
import io.github.lucasf1.vendas.domain.repository.ProdutoRepository;
import io.github.lucasf1.vendas.exception.RegraNegocioException;
import io.github.lucasf1.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.cliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.total());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItens(pedido, dto.items());
        repository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é permitido realizar um pedido sem itens");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.produto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(
                                () -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.quantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());
    }


}
