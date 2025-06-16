package com.example.Spring_Security.Service.Employee;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;
import com.example.Spring_Security.Model.TaskDto;

import java.util.List;

public interface EmployeeServiceInterface {
    public String saveProfile(Profile profile, User user);
    public ProfileDto findProfile(User user);
    public String updateProfile(Profile profile, Long profileId);
    public String updateProfileByAdmin(ProfileDto profileDto,Long userId);
   // public List<Task> findTaskByUser(User user);
    public List<TaskDto> findTaskByUser(User user);
    public Task updateTask(Long userId, Task task);
}
