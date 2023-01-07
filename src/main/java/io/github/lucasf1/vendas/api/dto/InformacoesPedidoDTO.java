package io.github.lucasf1.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record InformacoesPedidoDTO(
        Integer codigo,
        String cpf,
        String nomeCliente,
        BigDecimal total,
        String dataPedido,
        List<InformacaoItemPedidoDTO> items) {

}
