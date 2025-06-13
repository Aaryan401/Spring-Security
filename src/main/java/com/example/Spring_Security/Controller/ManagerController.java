package com.example.Spring_Security.Controller;

import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Service.Manager.ManagerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/manager/v1")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    @Autowired
    private final ManagerServiceImpl managerService;

    @PostMapping("create-task/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable("userId") Long assignUserId, @RequestBody Task task, @AuthenticationPrincipal User user){
        Task saveTask = managerService.createTask(task, user, assignUserId);
        return new ResponseEntity<>(saveTask, HttpStatus.CREATED);
    }
}
