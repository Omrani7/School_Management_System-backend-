package com.Omrani.med.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {
private final StudentService studentService;
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }
//    @PostMapping("/students")
//    public Student post(@RequestBody Student student) throws Exception {
//        // Ensure that the student object contains only the school ID
//        Integer  schoolId = student.getSchool().getId();
//        School school = schoolRepository.findById(schoolId)
//                .orElseThrow(() -> new Exception("School not found"));
//
//        student.setSchool(school);
//        return rep.save(student);
//    }

    @GetMapping("students/{student-id}")
    public StudentResponceDTO getStudentById(
            @PathVariable ("student-id") long id
    ){
 return studentService.getStudentById(id);
    }


    @GetMapping("students")
    public List<StudentResponceDTO> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("students/search/{name}")
    public  List<StudentResponceDTO>findStudentByName(
            @PathVariable("name") String name
    ){
      return  studentService.findStudentByName(name);
    }
    @DeleteMapping("students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public  void delete( @PathVariable ("student-id") long id){
        studentService.deleteStudentById(id);
    }
    @PostMapping("/students")
    public StudentResponceDTO saveStudent(@Valid @RequestBody StudentDTO student) {

        return studentService.saveStudent(student);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
    exp.getBindingResult().getAllErrors().forEach(error ->{
        var fieldName = ((FieldError)error).getField();
        var errorMessage = error.getDefaultMessage();
        errors.put(fieldName,errorMessage);
    });

return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}
