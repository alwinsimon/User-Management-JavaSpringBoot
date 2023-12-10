package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationRequest;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationResponse;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        //
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        //
    }

}
