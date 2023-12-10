package com.alwinsimon.UserManagementJavaSpringBoot.Config.Filter;

import com.alwinsimon.UserManagementJavaSpringBoot.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

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

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Fetch user details from DB using userDetailsService.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwtFromRequest, userDetails)) {
                // Authenticate user as the Token is Valid

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Update Security Context Holder with authToken
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        // Passing the control to next filters for continuation of execution.
        filterChain.doFilter(request, response);

    }
}
