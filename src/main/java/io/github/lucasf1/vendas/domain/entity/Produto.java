package io.github.lucasf1.vendas.domain.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    
    private Integer id;
    private String descricao;
    private BigDecimal preco;
}
