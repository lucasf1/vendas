package io.github.lucasf1.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.lucasf1.domain.entity.Cliente;

@Repository
public class ClienteRepository {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";
    private static String SELECT_ALL = "select * from cliente";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {

        jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {

        jdbcTemplate.update(UPDATE, new Object[] { cliente.getNome(), cliente.getId() });
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {

        jdbcTemplate.update(DELETE, new Object[] { id });
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
            SELECT_ALL.concat(" where nome like ?"), 
            new Object[] { "%" +nome + "%" },
            obterMapper());
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterMapper());
    }

    private RowMapper<Cliente> obterMapper() {
        return new RowMapper<Cliente>() {

            @Override
            public Cliente mapRow(ResultSet rs, int arg1) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
