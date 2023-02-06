package io.github.lucasf1.vendas.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    @Value("${api.security.token.expiracao}")
    private String expiracaoMinutos;

    @Value("${api.security.token.secret}")
    private String chaveAssinatura;

    public String gerarToken(UserDetails usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(chaveAssinatura);
            return JWT.create()
                .withIssuer("API Vendas")
                .withSubject(usuario.getUsername())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(chaveAssinatura);
            return JWT.require(algoritmo)
                .withIssuer("API Vendas")
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {

        long expiracao = Long.valueOf(expiracaoMinutos);
        return LocalDateTime.now().plusMinutes(expiracao).toInstant(ZoneOffset.of("-03:00"));
    }
}
