package io.github.lucasf1.vendas.api.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record InformacaoItemPedidoDTO(
        String descricaoProduto,
        BigDecimal precoUnitario,
        Integer quantidade) {
}
