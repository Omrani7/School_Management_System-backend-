package com.Omrani.med.student;

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
                "nabil@gmail.com",
                21);
        var student = mapper.toStudent(dto);
        Assertions.assertEquals(dto.firstName(),student.getFirstName());
        assertEquals(dto.lastName(),student.getLastName());
        assertEquals(dto.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(),student.getSchool().getId());

    }
    @Test
    public void toStudentResponceDto(){
        var student = new Student("omrani","mohamed",22,"mohamed@gmail.com");
        var responce = mapper.convert(student);
        assertEquals(responce.firstName(),student.getFirstName());
        assertEquals(responce.email(),student.getEmail());

    }
    @Test
    public void  should_map_student_to_studentDto_when_studentDto_isNull() {
        var exp = assertThrows(IllegalArgumentException.class, () -> mapper.toStudent(null));
        assertEquals("student must not be null", exp.getMessage());

    }
}