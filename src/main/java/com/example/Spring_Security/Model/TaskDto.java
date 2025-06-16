package com.example.Spring_Security.Model;


import com.example.Spring_Security.Entity.TaskStatus;
import com.example.Spring_Security.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TaskDto {
    private Long taskId;
    private String employeeName;
    private String managerName;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate creation;
    private LocalDate deadline;
    private LocalDate completionDate;
}
