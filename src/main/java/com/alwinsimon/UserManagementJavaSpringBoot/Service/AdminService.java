package com.alwinsimon.UserManagementJavaSpringBoot.Service;

import com.alwinsimon.UserManagementJavaSpringBoot.Model.User;
import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    void deleteUserByEmail(String email);

}
