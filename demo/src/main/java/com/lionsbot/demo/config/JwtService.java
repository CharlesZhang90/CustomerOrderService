package com.lionsbot.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "556A586E327235753878214125442A472D4B6150645367566B59703373367639";

	//extract username base on claim subject
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	//extract base on claim
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	//generate token base on userDetails
	public String generateToken(UserDetails userDetails) {
		return createToken(new HashMap<>(), userDetails);
	}
	
	//create jwt Token 
	public String createToken(Map<String, Object> claims, UserDetails userDetails) {
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(userDetails.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				   .signWith(getSignInKey(), SignatureAlgorithm.HS256)
				   .compact();
	}
	
	//check if token is valid based on username and expiration date
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	//check if token have expired
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	//extract expiration date base on expiration claim
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	//extract all claims from jwt token
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				   .setSigningKey(getSignInKey())
				   .build()
				   .parseClaimsJws(token)
				   .getBody();
	}

	//create sign in key
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
}
