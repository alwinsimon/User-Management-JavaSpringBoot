package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/current-user")
    public User getCurrentUser() {

        // API Endpoint to get the LoggedIn User Details using Token received in the Request Header.
        return userService.currentUserDetails();
        
    }
}
