package com.example.Spring_Security.Model;


import com.example.Spring_Security.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @NotBlank(message = "First name should not be blank")
    @Pattern(regexp = "^[a-zA-Z]$", message = "First name should have character only")
    private String firstName;

    @NotBlank(message = "Last name should not be blank")
    @Pattern(regexp = "^[a-zA-Z]$", message = "Last name should have character only")
    private String lastName;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 8,max = 16, message = "Password should be at least 8 characters")
    private String password;

    @NotBlank(message = "Role should not be blank")
    @Pattern(regexp = "^[A-Z]$", message = "Role should be in Capital letter only")
    private Role role;
}
