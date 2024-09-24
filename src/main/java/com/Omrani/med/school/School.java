package com.Omrani.med.school;

import com.Omrani.med.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class School {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
String name;
String score ;
    @OneToMany(
            mappedBy = "school",cascade = CascadeType.ALL

    )
    @JsonManagedReference
    private List<Student> students;
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }



    public School(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public School() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getId() {
  return  Id ; }
}

