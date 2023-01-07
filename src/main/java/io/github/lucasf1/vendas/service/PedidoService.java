package io.github.lucasf1.vendas.service;

import java.util.Optional;

import io.github.lucasf1.vendas.api.dto.PedidoDTO;
import io.github.lucasf1.vendas.domain.entity.Pedido;

public interface PedidoService {
    
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
