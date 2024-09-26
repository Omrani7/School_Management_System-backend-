package com.Omrani.med;

import jakarta.persistence.*;

@MappedSuperclass

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected   long id;

    public User() {
    }

     protected String userName;




    @Enumerated(EnumType.STRING)
    protected Role role;
    protected int age;

    public User(String userName,  Role role, int age, String email) {
        this.userName = userName;

        this.role = role;
        this.age = age;
        this.email = email;
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

   protected String email;


}
