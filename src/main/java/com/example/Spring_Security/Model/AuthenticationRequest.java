package com.example.Spring_Security.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/*** When user want to login this DTO will execute*/

@Data
@Builder
public class AuthenticationRequest {

    @NotBlank(message = "Username should not be blank")
    @Email(message = "userName should be email")
    private String userName;

    @NotBlank(message = "Password should not be blank")
    private String password;

}
