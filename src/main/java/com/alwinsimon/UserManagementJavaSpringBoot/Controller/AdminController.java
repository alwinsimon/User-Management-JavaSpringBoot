package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get-users")
    @Secured("ADMIN")
    public ResponseEntity<List<User>> getAllUsers() {

        // API Endpoint to get the LoggedIn User Details using Token received in the Request Header.
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @DeleteMapping("/delete-user/{email}")
    @Secured("ADMIN")
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
        try {
            adminService.deleteUserByEmail(email);
            return ResponseEntity.ok("User deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User deletion Failed.");
        }
    }
}
