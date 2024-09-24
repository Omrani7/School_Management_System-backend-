package com.Omrani.med.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
   private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;

    }


    @PostMapping("/school")
    public SchoolDto create(@RequestBody SchoolDto school){
return schoolService.saveSchool(school);
    }


public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName(), school.getScore());
}
    @GetMapping("/school")
    public List<SchoolDto> findAll(){

        return  schoolService.getAllSchools();
    }
    @DeleteMapping("school/{school-id}")
    public void deleteById(@PathVariable("school-id") Integer id ){
        schoolService.deleteById(id);
}
}
