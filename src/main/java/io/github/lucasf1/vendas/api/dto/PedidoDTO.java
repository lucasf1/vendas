package io.github.lucasf1.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoDTO(Integer cliente, BigDecimal total, List<ItemPedidoDTO> items) {
    
}
