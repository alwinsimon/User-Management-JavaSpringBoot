package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

import org.springframework.web.bind.annotation.*;
//import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    @PostMapping("/register")
    public String addUser(@RequestBody String requestBody){
        System.out.println("Received request body: " + requestBody);
        return requestBody;
    }
}
