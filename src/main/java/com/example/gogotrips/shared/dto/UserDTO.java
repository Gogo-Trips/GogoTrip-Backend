package com.example.gogotrips.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;

    @Email
    private String email;
}