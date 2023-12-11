package com.alwinsimon.UserManagementJavaSpringBoot.Service.Implementation;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import com.alwinsimon.UserManagementJavaSpringBoot.Repository.UserRepository;
import com.alwinsimon.UserManagementJavaSpringBoot.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {

        // Fetch and Returns All users from DB
        return userRepository.findAll();

    }

    public void deleteUserByEmail(String email) {

        User userToDelete = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        try {

            userRepository.deleteById(userToDelete.getId());

        } catch (EmptyResultDataAccessException e) {

            throw new UsernameNotFoundException("User Deletion FAILED.", e);

        }

    }

}
