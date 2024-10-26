package com.Omrani.med.student;

import com.Omrani.med.user.User;
import com.Omrani.med.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService( StudentRepository studentRepository, StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    public StudentResponceDTO saveStudent(StudentDTO dto){




        Student stud = studentMapper.toStudent(dto);

        var savedstudent = studentRepository.save(stud);

        return studentMapper.convert(savedstudent);
    }
    public StudentResponceDTO getStudentById(long id){
          return studentRepository.findById(id).map(studentMapper::convert).orElse(null);

    }
    public List<StudentResponceDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(studentMapper::convert).collect(Collectors.toList());
      }
    public List<StudentResponceDTO> findStudentByName(String name){
         return studentRepository.findAllByfirstnameContaining(name).stream().map(studentMapper::convert).collect(Collectors.toList());
    }
    public void deleteStudentById(long id){
        studentRepository.deleteById(id);
    }
    public void delete_all_students(){
        studentRepository.deleteAll();
    }
}
