package com.codedance2023.rsblock.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String getJwt(Map<String,Object> claims) {

        String jwt = Jwts.builder().
                setClaims(claims).   //自定义信息
                signWith(SignatureAlgorithm.HS256, "CodeDance2023").  //密钥——>生成数字签名用到
                setExpiration(new Date(System.currentTimeMillis() + 3600 * 24 * 1000)).  //有效期
                compact();  //生成
        return jwt;
    }

    //解析令牌
    public static Claims checkJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey("CodeDance2023").  //密钥
                parseClaimsJws(jwt).  //解析令牌
                getBody();
        return claims;
    }
}
