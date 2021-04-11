package com.cdms.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	private static String key = "eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3Jl";
	
	public static String generateToken(String subject) {
		return Jwts.builder()
				.setClaims(null)
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 24))
				.signWith(SignatureAlgorithm.HS512,key)
				.compact();
    }
	
	public static String parseToken(String token) {
        String subject = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

}
