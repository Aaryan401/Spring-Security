package com.example.Spring_Security.Controller;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;
import com.example.Spring_Security.Service.Employee.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * This is accessible by Employee and Admin only because we have given hasAnyRole("EMPLOYEE","ADMIN")
 */

@RestController
@RequestMapping("api/employee/v1")
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImpl employeeService;

    @GetMapping("greet")
    public String greet(){
        return "hi this Employee dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("meet")
    public String meet(){
        return "Hi this Admin but I can access this method from Employee";
    }

    @PostMapping("create-profile")
    public ResponseEntity<String> createProfile(@Valid @RequestBody Profile profile, @AuthenticationPrincipal User user){
        String response = employeeService.saveProfile(profile, user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-profile")
    public ResponseEntity<ProfileDto> getProfile(@AuthenticationPrincipal User user){
        ProfileDto profile = employeeService.findProfile(user);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    @PutMapping("update-profile/{pId}")
    public ResponseEntity<String> updateProfile(@PathVariable("pId") Long profileId,@Valid @RequestBody Profile profile ){
        String response = employeeService.updateProfile(profile, profileId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update-profile")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody ProfileDto profileDto,@AuthenticationPrincipal User user ){
        String response = employeeService.updateProfileByAdmin(profileDto, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
