package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Principal principal;

    public User currentUserDetails() {
        String email = principal.getName();
        return userRepository.findByEmail(email).orElse(null);
    }
}
