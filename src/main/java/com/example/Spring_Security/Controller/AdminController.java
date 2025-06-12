package com.example.Spring_Security.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This can only be access by Admin because we have given hasRole("Admin")
 */

@RestController
@RequestMapping("api/admin/v1")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("greet")
    public String greet(){
        return "hi this Admin dashboard";
    }
}
