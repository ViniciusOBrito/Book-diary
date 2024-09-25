package com.brito.bookdiary.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.brito.bookdiary.admin.Admin;
import com.brito.bookdiary.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class TokenService {

    @Value("${jwt.secret.key}")
    private String jwtKey;

    public Algorithm getAlgorithm(){
        return Algorithm.HMAC256(jwtKey);
    }

    public String generateToken(String subject,String role){
        try{
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(subject)
                    .withClaim("role", role)
                    .withExpiresAt(generateExpirationInstant())
                    .sign(getAlgorithm());
        }catch (Exception e){
            throw new RuntimeException("Error in creation of token", e);
        }
    }

    public boolean validateToken(String token){
        try {
            JWT.require(getAlgorithm())
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String generateUserToken(User user){
        return generateToken(user.getEmail(), "USER");
    }

    public String generateAdminToken(Admin admin){
        return generateToken(admin.getEmail(), "ADMIN");
    }

    public String getEmailFromToken(String token){
        return JWT.require(getAlgorithm())
                .withIssuer("login-auth-api")
                .build()
                .verify(token)
                .getSubject();
    }

    public String getRoleFromToken(String token){
        return JWT.require(getAlgorithm())
                .withIssuer("login-auth-api")
                .build()
                .verify(token)
                .getClaim("role").toString();
    }

    public Instant getExpirationTimeFromToken(String token){
        return JWT.require(getAlgorithm())
                .withIssuer("login-auth-api")
                .build()
                .verify(token)
                .getExpiresAt()
                .toInstant();
    }

    public Instant generateExpirationInstant(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
