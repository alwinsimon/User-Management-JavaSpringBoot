package com.alwinsimon.UserManagementJavaSpringBoot.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "_user",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
}
