package com.example.Spring_Security.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {
    private Long profileId;

    @Schema(
            description = "Full Name of the User like: ",example="Aaryan Prashar"
    )
    private String fullName;
    private int age;
    private String email;
    private String mobile;
    private String city;
    private String address;
}
