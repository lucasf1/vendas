package io.github.lucasf1.vendas.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

    @NotBlank(message = "{campo.login.obrigatorio}")
    String login,

    @NotBlank(message =  "{campo.senha.obrigatorio}")
    String password
) {

}
