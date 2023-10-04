package com.api.login.auth.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.login.usuario.model.UsuarioWeb;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioWeb usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
            .withIssuer("auth-api")
            .withSubject(usuario.getUsuario())
            .withExpiresAt(genExpirationDate())
            .withClaim("id", usuario.getUsuarioWebId())
            .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
           throw new RuntimeException("Erro ao gerar Token: "+e);
        }

    

    }

    public String validateToken(String token){
    
         try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)        
            .withIssuer("auth-api")
            .build()
            .verify(token)
            .getSubject();
        
        } catch (JWTVerificationException e) {
           throw new RuntimeException("Erro ao Validar Token"+e);
                    
        }

    }



        private Instant genExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    
}
