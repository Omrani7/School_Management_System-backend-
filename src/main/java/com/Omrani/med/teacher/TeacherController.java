package com.Omrani.med.teacher;

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
        Teacher teacher = teacherService.saveTeacher(new Teacher(
                teacherDTO.getUserName(),
                teacherDTO.getRole(),
                teacherDTO.getAge(),
                teacherDTO.getEmail(),
                teacherDTO.getSubjectSpecialization(),
                teacherDTO.getContactInformation()
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
            updatedTeacher.setUserName(teacherDetails.getUserName());
            updatedTeacher.setRole(teacherDetails.getRole());
            updatedTeacher.setAge(teacherDetails.getAge());
            updatedTeacher.setEmail(teacherDetails.getEmail());
            updatedTeacher.setSubjectSpecialization(teacherDetails.getSubjectSpecialization());
            updatedTeacher.setContactInformation(teacherDetails.getContactInformation());
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
