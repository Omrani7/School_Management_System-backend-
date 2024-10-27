package com.Omrani.med.teacher;

import com.Omrani.med.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers().stream()
                .map(teacher -> new TeacherResponseDTO(
                        teacher.getId(),
                        teacher.getUserName(),
                        teacher.getEmail(),
                        teacher.getSubjectSpecialization(),
                        teacher.getContactInformation()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(t -> ResponseEntity.ok(new TeacherResponseDTO(
                        t.getId(),
                        t.getUserName(),
                        t.getEmail(),
                        t.getSubjectSpecialization(),
                        t.getContactInformation())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TeacherResponseDTO createTeacher(@RequestBody TeacherDTO teacherDTO) {
        // Extracting UserDTO from TeacherDTO to create a Teacher entity
        UserDto userDTO = teacherDTO.user();
        Teacher teacher = teacherService.saveTeacher(new Teacher(
                userDTO.username(),
                userDTO.role(),
                userDTO.age(),
                userDTO.email(),
                teacherDTO.subjectSpecialization(),
                teacherDTO.contactInformation()
        ));
        return new TeacherResponseDTO(teacher.getId(), teacher.getUserName(),
                teacher.getEmail(), teacher.getSubjectSpecialization(),
                teacher.getContactInformation());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDetails) {
        Optional<Teacher> teacherOptional = teacherService.getTeacherById(id);
        if (teacherOptional.isPresent()) {
            Teacher updatedTeacher = teacherOptional.get();
            UserDto userDTO = teacherDetails.user();
            updatedTeacher.setUserName(userDTO.username());
            updatedTeacher.setRole(userDTO.role());
            updatedTeacher.setAge(userDTO.age());
            updatedTeacher.setEmail(userDTO.email());
            updatedTeacher.setSubjectSpecialization(teacherDetails.subjectSpecialization());
            updatedTeacher.setContactInformation(teacherDetails.contactInformation());
            teacherService.saveTeacher(updatedTeacher);
            return ResponseEntity.ok(new TeacherResponseDTO(
                    updatedTeacher.getId(),
                    updatedTeacher.getUserName(),
                    updatedTeacher.getEmail(),
                    updatedTeacher.getSubjectSpecialization(),
                    updatedTeacher.getContactInformation()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (teacherService.getTeacherById(id).isPresent()) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
