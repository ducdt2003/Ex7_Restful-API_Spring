package com.example.Ex7_Restful.API_Spring.controller;

import com.example.Ex7_Restful.API_Spring.dto.response.AuthResponse;
import com.example.Ex7_Restful.API_Spring.entity.User;
import com.example.Ex7_Restful.API_Spring.repository.UserRepository;
import com.example.Ex7_Restful.API_Spring.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String username,
                                              @RequestParam String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        userRepository.save(user);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}