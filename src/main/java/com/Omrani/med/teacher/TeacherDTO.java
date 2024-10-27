package com.Omrani.med.teacher;

import com.Omrani.med.user.UserDto;

public record TeacherDTO(
        UserDto user,
        String subjectSpecialization,
        String contactInformation
) {}
