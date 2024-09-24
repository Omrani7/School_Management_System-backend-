package com.Omrani.med.school;

import com.Omrani.med.school.SchoolDto;
import com.Omrani.med.school.SchoolMapper;
import com.Omrani.med.school.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
private final SchoolMapper schoolMapper;
    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }
public SchoolDto saveSchool(SchoolDto school){
        var s = schoolMapper. toSchool(school);
    var saved =   schoolRepository.save(s);
    return school;
}
public List<SchoolDto> getAllSchools(){
    return  schoolRepository.findAll() .stream().map(schoolMapper::toSchoolDto).collect(Collectors.toList());
}
public void deleteById(Integer id){
    schoolRepository.deleteById(id);
}
}
