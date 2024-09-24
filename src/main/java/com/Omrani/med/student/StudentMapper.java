package com.Omrani.med.student;

import com.Omrani.med.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDTO student){
        if (student == null){
            throw new IllegalArgumentException("student must not be null");
        }
        Student s = new Student();
        s.setEmail(student.email());
        s.setFirstName(student.firstName());
        s.setLastName(student.lastName());
        School school = new School();
        school.setId(student.schoolId());
        s.setSchool(school);
        return  s;
    }
    public StudentResponceDTO convert(Student student){
        return new StudentResponceDTO(student.getFirstName(),student.getLastName(),
                 student.getEmail());
    }
}
