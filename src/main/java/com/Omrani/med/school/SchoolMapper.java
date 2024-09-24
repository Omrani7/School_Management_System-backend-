package com.Omrani.med.school;

import com.Omrani.med.school.SchoolDto;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public School toSchool(SchoolDto school) {
        return  new School(school.name(),school.score());
    }
    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName(), school.getScore());
}}
