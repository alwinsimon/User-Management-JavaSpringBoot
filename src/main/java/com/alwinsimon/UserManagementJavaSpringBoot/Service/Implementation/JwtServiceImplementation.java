package com.alwinsimon.UserManagementJavaSpringBoot.Service.Implementation;

import com.alwinsimon.UserManagementJavaSpringBoot.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImplementation implements JwtService {

    // Set Signing Key for Signing JWT Tokens
    private static final String JWT_SECRET_KEY = "2F66554775577754346D56726D7933315266773239773D3D";

    private Key getJwtSigningKey() {
        // Function to provide JWT_SECRET_KEY

        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String extractUsername(String jwtToken) {
        // Decrypt jwt token and return Username

        return extractClaimFromJwtToken(jwtToken, Claims::getSubject);

    }

    private Claims extractAllClaimsFromJwtToken(String jwtToken) {
        // Function to Extract ALL claims from JWT Token

        return Jwts
                .parserBuilder()
                .setSigningKey(getJwtSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

    }

    public <T> T extractClaimFromJwtToken(String jwtToken, Function<Claims, T> claimsResolver) {
        // Function to Extract single claim from JWT Token

        final Claims claims = extractAllClaimsFromJwtToken(jwtToken);
        return claimsResolver.apply(claims);

    }

    public String generateJwtToken(Map<String, Object> additionalClaims, UserDetails userDetails) {
        /**
         * Function to generate JWT Token With Additional Claims
         */

        return Jwts
                .builder()
                .setClaims(additionalClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getJwtSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateJwtToken(UserDetails userDetails) {
        /**
         * Function to generate JWT Token without any extra claims
         * Internally uses generateJwtToken method which takes in additional claims
         * In place of additional claims, passes an empty HashMap
         */

        return generateJwtToken(new HashMap<>(), userDetails);

    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        /**
         * Function to check the provided token is valid for the user and is not expired.
         */

        final String userName = extractUsername(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));

    }

    private boolean isTokenExpired(String jwtToken) {
        /**
         * Function to check the provided token is valid for the user and is not expired.
         */

        return extractJwtTokenExpiration(jwtToken).before(new Date());

    }

    private Date extractJwtTokenExpiration(String jwtToken) {
        /**
         * Function to return the expiration date of a provided Jwt Token
         * Internally uses extractClaimFromJwtToken method which takes in token and a single required claim
         * In place of single required claims, passes a claim to get expiration
         */

        return extractClaimFromJwtToken(jwtToken, Claims::getExpiration);

    }
}
