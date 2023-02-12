package io.github.lucasf1.vendas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucasf1.vendas.api.dto.CredenciaisDTO;
import io.github.lucasf1.vendas.api.dto.DadosCadastroUsuario;
import io.github.lucasf1.vendas.api.dto.TokenDTO;
import io.github.lucasf1.vendas.domain.entity.Usuario;
import io.github.lucasf1.vendas.domain.repository.UsuarioRepository;
import io.github.lucasf1.vendas.service.TokenService;
import io.github.lucasf1.vendas.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid DadosCadastroUsuario dadosUsuario) {

        String senhaCriptograda = passwordEncoder.encode(dadosUsuario.password());
        Usuario usuario = new Usuario(dadosUsuario);
        usuario.setSenha(senhaCriptograda);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody CredenciaisDTO credenciais) {

        var authToken = new UsernamePasswordAuthenticationToken(
                credenciais.login(), credenciais.senha());
        var authentication = manager.authenticate(authToken);

        var usuario = (UserDetails) authentication.getPrincipal();
        String tokenJWT = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenDTO(usuario.getUsername(), tokenJWT));
    }
}
