package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationRequest;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.AuthenticationResponse;
import com.alwinsimon.UserManagementJavaSpringBoot.Config.Auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
