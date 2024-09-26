package com.Omrani.med;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        @NotEmpty
        String username ,
         @NotEmpty
          String email,
          @NotEmpty
          int age,
           @NotEmpty
            Role role) {
}
