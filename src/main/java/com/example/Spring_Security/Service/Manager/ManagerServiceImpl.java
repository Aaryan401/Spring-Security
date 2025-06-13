package com.example.Spring_Security.Service.Manager;


import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.TaskStatus;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Repository.TaskRepository;
import com.example.Spring_Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerServiceInterface {

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Task createTask(Task task, User user, Long assignUserId) {
        User assignedUser = userRepository.findById(assignUserId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setAssignTo(assignedUser);
        task.setAssignBy(user);
        task.setStatus(TaskStatus.ASSIGNED);
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }
}
