package com.example.Spring_Security.Service.Manager;

import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.User;

public interface ManagerServiceInterface {
    public Task createTask(Task task, User user, Long assignUserId);
}
