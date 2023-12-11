package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String jwtToken);

    <T> T extractClaimFromJwtToken(String jwtToken, Function<Claims, T> claimsResolver);

    String generateJwtToken(Map<String, Object> additionalClaims, UserDetails userDetails);

    String generateJwtToken(UserDetails userDetails);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

}
