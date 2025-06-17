package com.example.Spring_Security.Controller;

import com.example.Spring_Security.Entity.Profile;
import com.example.Spring_Security.Entity.Task;
import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.ProfileDto;
import com.example.Spring_Security.Model.TaskDto;
import com.example.Spring_Security.Service.Employee.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is accessible by Employee and Admin only because we have given hasAnyRole("EMPLOYEE","ADMIN")
 */

@RestController
@RequestMapping("api/employee/v1")
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequiredArgsConstructor

/**
 *This is Swagger Annotation which give the information about the Controller behaviour
 */
@Tag(name = "Employee Controller",
     description = "This Controller Contains all the APIs related to the Employee, like- creating, updating, retrieving"
)
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImpl employeeService;

    @Hidden
    @Operation(
            summary = "Test api",
            description = "This API is for test purposes only."
    )
    @GetMapping("greet")
    public String greet(){
        return "hi this Employee dashboard";
    }

    @Operation(
            summary = "Test API for Admin",
            description = "This API can only be tested by ADMIN."
    )
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

    @Operation(
            summary ="This API updating the profile details using the Profile id, need to pass the profile id with API"
    )
    @PutMapping("update-profile/{pId}")
    public ResponseEntity<String> updateProfile(@Parameter(description = "ID of the profile")
                                                @PathVariable("pId") Long profileId,
                                                @Valid @RequestBody Profile profile ){
        String response = employeeService.updateProfile(profile, profileId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update-profile-by-admin/{userId}")
    public ResponseEntity<String> updateProfileByAdmin(@PathVariable Long userId ,@Valid @RequestBody ProfileDto profileDto){
        String response = employeeService.updateProfileByAdmin(profileDto,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "To get all the task of the Logged in User"
    )
    @ApiResponses( value={
        @ApiResponse(
            description = "OK: If task find Successfully It will return the json data of the list of Tasks.",
            responseCode = "200"
    ),
        @ApiResponse(responseCode = "403",
                     description = "Forbidden: If the user is not authorized to access the resource."
        )
}
    )
    @GetMapping("find-task")
    public ResponseEntity<List<TaskDto>> getTask(@AuthenticationPrincipal User user){
        List<TaskDto> taskByUser = employeeService.findTaskByUser(user);
        return new ResponseEntity<>(taskByUser, HttpStatus.OK);
    }



}
