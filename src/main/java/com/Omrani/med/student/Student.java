package com.Omrani.med.student;


import com.Omrani.med.Role;
import com.Omrani.med.User;
import com.Omrani.med.profile.Profile;
import com.Omrani.med.school.School;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table
public class Student  extends User {


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
private String  firstname;
private String lastname;
;

    public Student() {
    }
    public Student(String userName, Role role,
                   int age, String email,
                    String firstname, String lastname) {
        super(userName, role, age, email);

        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Student(String userName, Role role,
                   int age, String email, School school,
                   Profile studentprofile, String firstname, String lastname) {
        super(userName, role, age, email);
        this.school = school;
        this.studentprofile = studentprofile;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
