package com.Omrani.med.profile;

import com.Omrani.med.student.Student;
import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    String bio;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne
@JoinColumn(
        name = "student_id"
)
    private Student student;
    public Profile(String bio) {
        this.bio = bio;
    }



    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
