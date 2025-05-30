package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.login.LoginRequestDTO;
import com.ogifmsim.fmsimulator.dto.login.LoginResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/football/login")
public class LoginController { 
    
    @PostMapping("")
    public ResponseEntity<?> postLogin(@RequestBody LoginRequestDTO loginRequstDTO) {
        if(loginRequstDTO.getUsername().equals("ogi") && loginRequstDTO.getPassword().equals("2390")) {
            String token = "zfa6h7e6cgh1qp87";
            return ResponseEntity.ok(new LoginResponseDTO("Login successful", token));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Invalid credentials", null));
        }
    }
    
    
}
