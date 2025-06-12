package com.example.Spring_Security.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {
    private Long profileId;
    private String fullName;
    private int age;
    private String email;
    private String mobile;
    private String city;
    private String address;
}
