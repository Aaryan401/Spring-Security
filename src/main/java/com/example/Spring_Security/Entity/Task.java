package com.example.Spring_Security.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime creation;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime completionDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private User assignTo;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private User assignBy;

    @PrePersist
    protected void onCreate() {
        this.creation = LocalDateTime.now();
        this.deadline = LocalDateTime.now().plusDays(2);
    }

}
