package com.example.Spring_Security.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;


    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Age must not be blank")
    @Size(min = 10, max = 13, message = "Mobile number must be 10 digits")
    @Column(nullable = false)
    private String mobile;

    @NotBlank(message = "Age must not be blank")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Age must not be blank")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Age must not be blank")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @NotBlank(message = "Age must not be blank")
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
