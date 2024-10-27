package com.Omrani.med.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 50) // Example validation
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int age;

    @NotNull
    @Email
    private String email;

    public User() {
    }

    public User(String userName, Role role, int age, String email) {
        this.userName = userName;
        this.role = role;
        this.age = age;
        this.email = email;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Ensure to hash this password before saving
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
