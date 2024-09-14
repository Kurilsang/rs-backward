package com.kuril.logindemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtitls {
    private static String signKey = "piyingxi";
    private static Long expire = 259200000L; // 三天

    public static String generateToken(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }
    public static Claims verifyToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
