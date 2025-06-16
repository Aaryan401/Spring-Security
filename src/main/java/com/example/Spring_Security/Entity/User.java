package com.example.Spring_Security.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private Role role;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdDate;

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

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return role.getAuthorities();
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @OneToMany(mappedBy = "assignTo",fetch = FetchType.EAGER)
        private List<Task> task = new ArrayList<>();

        @OneToMany(mappedBy = "assignBy",fetch = FetchType.EAGER)
        private List<Task> task1 = new ArrayList<>();
}
