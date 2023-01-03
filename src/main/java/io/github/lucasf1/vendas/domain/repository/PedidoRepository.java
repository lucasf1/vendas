package io.github.lucasf1.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucasf1.vendas.domain.entity.Cliente;
import io.github.lucasf1.vendas.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    
    List<Pedido> findByCliente(Cliente cliente);
}
