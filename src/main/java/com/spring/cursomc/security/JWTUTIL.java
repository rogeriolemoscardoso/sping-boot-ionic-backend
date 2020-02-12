package com.spring.cursomc.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUTIL {

    @Value("${jwt.secrete}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String userName) {
        return Jwts.builder().
                setSubject(userName).
                setExpiration(new Date(System.currentTimeMillis() + expiration)).
                signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();
    }
}
