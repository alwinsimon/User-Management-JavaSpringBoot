package com.alwinsimon.UserManagementJavaSpringBoot.Repository;

import java.util.Optional;
import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

    @Override
    void deleteById(Long id);

}
