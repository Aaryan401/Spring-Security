package com.example.Spring_Security.Repository;

import com.example.Spring_Security.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    public List<Task> findByAssignToUserId(Long userId);
}
