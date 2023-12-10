package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

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
                .parseClaimsJwt(jwtToken)
                .getBody();

    }

    public <T> T extractClaimFromJwtToken(String jwtToken, Function<Claims, T> claimsResolver) {
        // Function to Extract single claim from JWT Token

        final Claims claims = extractAllClaimsFromJwtToken(jwtToken);
        return claimsResolver.apply(claims);

    }

}
