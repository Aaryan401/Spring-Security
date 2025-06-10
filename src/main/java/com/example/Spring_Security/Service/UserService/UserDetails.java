package com.example.Spring_Security.Service.UserService;

import com.example.Spring_Security.Entity.User;
import com.example.Spring_Security.Model.AuthenticationResponse;
import com.example.Spring_Security.Model.RegisterRequest;
import com.example.Spring_Security.Repository.UserRepository;
import com.example.Spring_Security.Service.Utility.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetails {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;


//  method to register a user.
    public AuthenticationResponse saveUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }
}
