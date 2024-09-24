package com.Omrani.med.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
@Mock
private  StudentRepository studentRepository;
@Mock
private  StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void should_successfully_save_a_student(){
        StudentDTO dto  = new StudentDTO("john","imad","imad@gmail.com",22);

        Student student = new Student("imad","john",22,"imad@gmail.com");

        Student savedStudent = new Student("imad","john",22,"imad@gmail.com");
        savedStudent.setId(1);
        //mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.convert(savedStudent)).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
        StudentResponceDTO response = studentService.saveStudent(dto);
        assertEquals(dto.firstName(),response.firstName());
        assertEquals(dto.email(),response.email() );
        verify(studentMapper,times(1)).toStudent(dto);
        verify(studentRepository,times(1)).save(student);
        verify(studentMapper,times(1)).convert(savedStudent);

    }
    @Test
    public void should_return_allstudents(){
        // given
        var students = new ArrayList<Student>();
       students.add (new Student( "imad","john",22,"imad@gmail.com"));
//Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.convert(any(Student.class))).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
        List<StudentResponceDTO> list = studentService.getAllStudents();
        assertEquals(students.size(),list.size());
    }
    @Test
    public void should_get_the_student_byId(){
        long id = 29;
        var student = new Student("imad","john",22,"imad@gmail.com");
        student.setId(id);
        //mock calls
        when(studentRepository.findById(id)).thenReturn((Optional.of(student)));
        when(studentMapper.convert(any(Student.class))).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
       StudentResponceDTO responceDTO = studentService.getStudentById(id);
       assertEquals(student.getFirstName(),responceDTO.firstName());
        assertEquals(student.getLastName(),responceDTO.lastName());
       assertEquals(student.getEmail(),responceDTO.email());

    }
    @Test
    public void should_delete(){
       long id = 5;
       doNothing().when(studentRepository).deleteById(id);
       studentService.deleteStudentById(id);
    }
}