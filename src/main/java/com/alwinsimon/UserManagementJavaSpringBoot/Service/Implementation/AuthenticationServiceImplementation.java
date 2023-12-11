package com.alwinsimon.UserManagementJavaSpringBoot.Service.Implementation;

import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationRequest;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationResponse;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.RegisterRequest;
import com.alwinsimon.UserManagementJavaSpringBoot.Model.Role;
import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Repository.UserRepository;
import com.alwinsimon.UserManagementJavaSpringBoot.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImplementation jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        // Build a user using builder in user model.
        var user = User.builder()
                .name(request.getName())
                .gender(request.getGender())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        // Save User to DB using UserRepository
        userRepository.save(user);

        // Generate a JWT Token to return along with Response.
        var jwtToken = jwtService.generateJwtToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Try Authenticating user with Authentication Manager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        /**
         * If the authentication manager authenticated user without throwing any exception
         * Find user and generate auth token
         * Send auth token back to user.
         */

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // Generate a JWT Token to return along with Response.
        var jwtToken = jwtService.generateJwtToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
