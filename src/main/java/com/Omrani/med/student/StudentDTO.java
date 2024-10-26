package com.Omrani.med.student;

import com.Omrani.med.user.UserDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentDTO(
        @NotEmpty String firstname,
        @NotEmpty String lastname,

        @NotNull(message = "School ID must not be null")
        @Min(value = 1, message = "School ID must be greater than or equal to 1")
        Integer schoolId,

        @NotNull UserDto userdto
) {
}

