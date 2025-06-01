package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.login.LoginRequestDTO;
import com.ogifmsim.fmsimulator.dto.login.LoginResponseDTO;
import com.ogifmsim.fmsimulator.model.user.User;
import com.ogifmsim.fmsimulator.model.user.UserRole;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/football/login")
public class LoginController { 
    private User user = new User("MrTousty", "wasdwasd", UserRole.ADMIN);
    
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequstDTO) {
        if(loginRequstDTO.getUsername().equals(user.getUsername()) && loginRequstDTO.getPassword().equals("2390")) {
            String token = "zfa6h7e6cgh1qp87";
            return ResponseEntity.ok(new LoginResponseDTO("Login successful", token, user));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Invalid credentials", null, null));
        }
    }
    
    
}
