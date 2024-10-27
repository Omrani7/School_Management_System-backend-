package com.Omrani.med.teacher;



public record TeacherResponseDTO(
        Long id,
        String userName,
        String email,
        String subjectSpecialization,
        String contactInformation
) {}

