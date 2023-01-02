package io.github.lucasf1.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.lucasf1.vendas.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente where nome like '%:nome%'", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "delete from cliente where nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);

}