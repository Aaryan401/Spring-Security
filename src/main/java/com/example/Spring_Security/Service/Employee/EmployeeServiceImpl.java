package com.example.Spring_Security.Service.Employee;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;
import com.example.Spring_Security.Model.TaskDto;
import com.example.Spring_Security.Repository.ProfileRepository;
import com.example.Spring_Security.Repository.TaskRepository;
import com.example.Spring_Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeServiceInterface{

   @Autowired
   private final ProfileRepository profileRepository;

   @Autowired
   private final UserRepository userRepository;

   @Autowired
   private final TaskRepository taskRepository;

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
    public String updateProfileByAdmin(ProfileDto profileDto,Long userId) {
        Profile existingProfile = profileRepository.findByUserUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String[] str=profileDto.getFullName().split(" ");
        existingProfile.getUser().setFirstName(str[0]);
        existingProfile.getUser().setLastName(str[1]);
        existingProfile.getUser().setEmail(profileDto.getEmail());
        existingProfile.setAge(profileDto.getAge());
        existingProfile.setCity(profileDto.getCity());
        existingProfile.setAddress(profileDto.getAddress());
        existingProfile.setMobile(profileDto.getMobile());
        profileRepository.save(existingProfile);
        return "Your profile has been updated";
    }

    @Override
    public List<TaskDto> findTaskByUser(User user) {
        User foundUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Task> taskList = taskRepository.findByAssignToUserId(foundUser.getUserId());
        return taskList.stream().map(task -> {
            LocalDate completionDate = null;
            if ("COMPLETED".equalsIgnoreCase(String.valueOf(task.getStatus()))) {
                completionDate = LocalDate.now();
            }
            TaskDto taskDto = TaskDto.builder()
                    .taskId(task.getTaskId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .employeeName(task.getAssignTo().getFirstName()+" "+task.getAssignTo().getLastName())
                    .managerName(task.getAssignBy().getFirstName()+" "+task.getAssignBy().getLastName())
                    .status(task.getStatus())
                    .creation(LocalDate.from(task.getCreation()))
                    .deadline(LocalDate.from(task.getDeadline()))
                    .completionDate(completionDate)
                    .build();
            return taskDto;
        }).toList();
    }

//    @Override
//    public List<Task> findTaskByUser(User user) {
//        if(user.getTask().isEmpty()){
//            return new ArrayList<>() ;
//        }
//        return user.getTask();
//    }




    @Override
    public Task updateTask(Long userId, Task task) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        foundUser.getTask();
        task.setStatus(task.getStatus());
        taskRepository.save(task);
        return task;
    }


}
