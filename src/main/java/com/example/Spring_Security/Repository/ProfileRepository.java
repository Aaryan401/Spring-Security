package com.example.Spring_Security.Repository;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUserUserId(Long userId);
}
