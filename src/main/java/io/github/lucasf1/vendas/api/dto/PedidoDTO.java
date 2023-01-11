package io.github.lucasf1.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;

import io.github.lucasf1.vendas.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}") 
    Integer cliente, 

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    BigDecimal total,

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")     
    List<ItemPedidoDTO> items) {
    
}
