package io.github.lucasf1.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;

import io.github.lucasf1.vendas.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
    @NotNull(message = "Informe o nome do cliente.") 
    Integer cliente, 

    @NotNull(message = "Campo total do pedido é obrigatório.")
    BigDecimal total,

    @NotEmptyList(message = "O pedido deve ter itens.")     
    List<ItemPedidoDTO> items) {
    
}
