package com.alwinsimon.UserManagementJavaSpringBoot.Repository;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
