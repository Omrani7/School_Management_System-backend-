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
        s.setEmail(student.userdto().email());
        s.setFirstname(student.firstname());
        s.setLastname(student.lastname());
        s.setAge(student.userdto().age());
        s.setRole(student.userdto().role());
        s.setUserName(student.userdto().username());
        School school = new School();
        school.setId(student.schoolId());
        s.setSchool(school);
        return  s;
    }
    public StudentResponceDTO convert(Student student){
        return new StudentResponceDTO(student.getFirstname(),student.getLastname(),
                 student.getEmail());
    }
}
