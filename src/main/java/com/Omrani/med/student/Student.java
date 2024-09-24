package com.Omrani.med.student;


import com.Omrani.med.profile.Profile;
import com.Omrani.med.school.School;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String lastName;
    String firstName;
    int age;
    String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id",nullable = false)
    @JsonBackReference
    @JsonIgnoreProperties("students")
    private School school;
    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private Profile studentprofile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Profile getStudentprofile() {
        return studentprofile;
    }

    public void setStudentprofile(Profile studentprofile) {
        this.studentprofile = studentprofile;
    }


    public Student(String lastName, String firstName, int age, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
    }

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
