package com.Omrani.med.student;

import com.Omrani.med.user.Role;
import com.Omrani.med.user.UserDto;
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
        StudentDTO dto  = new StudentDTO("john","imad",22,new UserDto("imadgan","imad@gmail.com",23, Role.Student));

        Student student = new Student("imadgan",Role.Student,23,"imad@gmail.com","john","imad");

        Student savedStudent = new Student("imadgan",Role.Student,23,"imad@gmail.com","john","imad");


        //mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.convert(savedStudent)).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
        StudentResponceDTO response = studentService.saveStudent(dto);
        assertEquals(dto.firstname(),response.firstName());
        assertEquals(dto.userdto().email(),response.email() );
        verify(studentMapper,times(1)).toStudent(dto);
        verify(studentRepository,times(1)).save(student);
        verify(studentMapper,times(1)).convert(savedStudent);

    }
    @Test
    public void should_return_allstudents(){
        // given
        var students = new ArrayList<Student>();
       students.add ( new Student("imadgan",Role.Student,23,"imad@gmail.com","john","imad"));

//Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.convert(any(Student.class))).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
        List<StudentResponceDTO> list = studentService.getAllStudents();
        assertEquals(students.size(),list.size());
    }
    @Test
    public void should_get_the_student_byId(){
        long id = 29;
        var student =  new Student("imadgan",Role.Student,23,"imad@gmail.com","john","imad");


        //mock calls
        when(studentRepository.findById(id)).thenReturn((Optional.of(student)));
        when(studentMapper.convert(any(Student.class))).thenReturn(new StudentResponceDTO("john","imad","imad@gmail.com"));
       StudentResponceDTO responceDTO = studentService.getStudentById(id);
       assertEquals(student.getFirstname(),responceDTO.firstName());
        assertEquals(student.getLastname(),responceDTO.lastName());
       assertEquals(student.getEmail(),responceDTO.email());

    }
    @Test
    public void should_delete(){
       long id = 5;
       doNothing().when(studentRepository).deleteById(id);
       studentService.deleteStudentById(id);
    }
}