package io.github.lucasf1.vendas.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucasf1.vendas.api.dto.DadosCadastroUsuario;
import io.github.lucasf1.vendas.domain.entity.Usuario;
import io.github.lucasf1.vendas.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid DadosCadastroUsuario dadosUsuario) {

        String senhaCriptograda = passwordEncoder.encode(dadosUsuario.password());
        Usuario usuario = new Usuario(dadosUsuario);
        usuario.setSenha(senhaCriptograda);
        return usuarioService.salvar(usuario);
    }
}
