package io.github.lucasf1.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucasf1.vendas.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    
}
