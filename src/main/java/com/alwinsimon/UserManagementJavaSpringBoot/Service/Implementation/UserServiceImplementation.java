package com.alwinsimon.UserManagementJavaSpringBoot.Service.Implementation;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Repository.UserRepository;
import com.alwinsimon.UserManagementJavaSpringBoot.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final Principal principal;

    public User currentUserDetails() {
        String email = principal.getName();
        return userRepository.findByEmail(email).orElse(null);
    }
}
