package com.example.Spring_Security.Service.Employee;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;
import com.example.Spring_Security.Repository.ProfileRepository;
import com.example.Spring_Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeServiceInterface{

   @Autowired
   private final ProfileRepository profileRepository;

   @Autowired
   private final UserRepository userRepository;

    @Override
    public String saveProfile(Profile profile, User user) {
        //User foundUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        profileRepository.save(profile);
        return "Your profile has been saved";
    }

    @Override
    public ProfileDto findProfile(User user) {
        Profile existingProfile = profileRepository.findByUserUserId(user.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        ProfileDto profileDto = ProfileDto.builder()
                .profileId(existingProfile.getProfileId())
                .fullName(user.getFirstName()+" "+user.getLastName())
                .age(existingProfile.getAge())
                .email(user.getEmail())
                .mobile(existingProfile.getMobile())
                .city(existingProfile.getCity())
                .address(existingProfile.getAddress())
                .build();

        return profileDto;
    }

    @Override
    public String updateProfile(Profile profile, Long profileId) {
        Profile foundProfile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));

        foundProfile.setAge(profile.getAge());
        foundProfile.setMobile(profile.getMobile());
        foundProfile.setCity(profile.getCity());
        foundProfile.setAddress(profile.getAddress());

        profileRepository.save(foundProfile);

        return "profile has been updated!";
    }

    @Override
    public String updateProfileByAdmin(ProfileDto profileDto, User user) {
        Profile existingProfile = profileRepository.findByUserUserId(user.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        String[] str=profileDto.getFullName().split(" ");
        user.setFirstName(str[0]);
        user.setLastName(str[1]);
        user.setEmail(profileDto.getEmail());
        existingProfile.setAge(profileDto.getAge());
        existingProfile.setCity(profileDto.getCity());
        existingProfile.setAddress(profileDto.getAddress());
        existingProfile.setMobile(profileDto.getMobile());
        userRepository.save(user);
        profileRepository.save(existingProfile);
        return "Your profile has been updated";
    }


}
