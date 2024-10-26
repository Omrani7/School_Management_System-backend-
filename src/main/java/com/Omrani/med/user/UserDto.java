package com.Omrani.med.user;

import com.Omrani.med.user.Role;
import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        @NotEmpty
        String username ,
         @NotEmpty
          String email,
          @NotEmpty
          int age,
           @NotEmpty
        Role role,
        @NotEmpty
        String password) {
}
