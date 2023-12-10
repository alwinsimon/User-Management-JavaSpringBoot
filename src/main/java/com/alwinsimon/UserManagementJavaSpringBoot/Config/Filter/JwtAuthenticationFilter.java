package com.alwinsimon.UserManagementJavaSpringBoot.Config.Filter;

import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String jwtFromRequest;
        final String authHeaderFromRequest;
        final String userEmail;

        // Try to parse JWT from the request header if it exists.
        authHeaderFromRequest = request.getHeader("Authorization");

        // Return early if there is no valid authorization header.
        if (authHeaderFromRequest == null || !authHeaderFromRequest.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Parse and store JWT Token received from Authorisation Header.
        jwtFromRequest = authHeaderFromRequest.substring(7);

        // Decrypt Token and find user email from token claims
        userEmail = jwtService.extractUsername(jwtFromRequest);
    }
}
