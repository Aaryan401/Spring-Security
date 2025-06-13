package com.example.Spring_Security.Service.Employee;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;

public interface EmployeeServiceInterface {
    public String saveProfile(Profile profile, User user);
    public ProfileDto findProfile(User user);
    public String updateProfile(Profile profile, Long profileId);
    public String updateProfileByAdmin(ProfileDto profileDto, User user);
}
