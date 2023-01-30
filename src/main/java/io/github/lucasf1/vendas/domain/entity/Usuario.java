package io.github.lucasf1.vendas.domain.entity;

import io.github.lucasf1.vendas.api.dto.DadosCadastroUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String login;

    @Column
    private String senha;

    private boolean isAdmin;


    public Usuario(DadosCadastroUsuario dadosUsuario) {
        this.login = dadosUsuario.login();
        this.senha = dadosUsuario.password();
        this.isAdmin = false;
    }
}
