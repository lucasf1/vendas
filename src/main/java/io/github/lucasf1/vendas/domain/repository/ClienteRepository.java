package io.github.lucasf1.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucasf1.vendas.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    List<Cliente> findByNomeLike(String nome);

}