package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    public String extractUsername(String jwtToken);

    public <T> T extractClaimFromJwtToken(String jwtToken, Function<Claims, T> claimsResolver);

    public String generateJwtToken(Map<String, Object> additionalClaims, UserDetails userDetails);

    public String generateJwtToken(UserDetails userDetails);

    public boolean isTokenValid(String jwtToken, UserDetails userDetails);

}
