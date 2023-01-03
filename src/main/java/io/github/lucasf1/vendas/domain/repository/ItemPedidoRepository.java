package io.github.lucasf1.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucasf1.vendas.domain.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
    
}
