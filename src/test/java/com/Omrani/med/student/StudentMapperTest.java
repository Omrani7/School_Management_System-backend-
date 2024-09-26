package com.Omrani.med.student;

import com.Omrani.med.Role;
import com.Omrani.med.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void testToStudent() {
        StudentDTO dto = new StudentDTO(
                "nabil",
                "karoui",
                21,
                new UserDto(
                        "nabil","nabil@gmail.com",55, Role.Student
                )
                );
        var student = mapper.toStudent(dto);
        Assertions.assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.userdto().email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(),student.getSchool().getId());

    }
    @Test
    public void toStudentResponceDto(){
        var student = new Student("lking",Role.Student,22,"mohamed@gmail.com","omrani","mohamed");
        var responce = mapper.convert(student);
        assertEquals(responce.firstName(),student.getFirstname());
        assertEquals(responce.email(),student.getEmail());

    }
    @Test
    public void  should_map_student_to_studentDto_when_studentDto_isNull() {
        var exp = assertThrows(IllegalArgumentException.class, () -> mapper.toStudent(null));
        assertEquals("student must not be null", exp.getMessage());

    }
}