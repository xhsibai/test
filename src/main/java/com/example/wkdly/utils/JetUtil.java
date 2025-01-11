package com.example.wkdly.utils;

import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;



import java.util.Date;

import java.util.Map;
public class JetUtil {
    public static String getJwtToken(Map<String, Object> claims) {
        return JWT.create() .withClaim("claim",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+60L*60*1000))
                .sign(Algorithm.HMAC256("wkdly"));

    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256("wkdly")).build().verify(token).getClaim("claim").asMap();

    }
}
