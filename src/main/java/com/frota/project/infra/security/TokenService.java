package com.frota.project.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.frota.project.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String genereteToken(UserModel user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("frota_api")
                    .withSubject(user.getId_user().toString())
                    .withExpiresAt(genExpirationToken())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao criar Token");
        }
    }

    public String validationToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("frota_api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            throw new RuntimeException("Erro ao Validar Token");
        }
    }

     private Instant genExpirationToken() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }

    // DESTA FORMA PRECISA FICAR SEMPRE ATENTO NO HORARIO DO SERVIDOR
    /*private Instant genExpirationToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }*/

}
