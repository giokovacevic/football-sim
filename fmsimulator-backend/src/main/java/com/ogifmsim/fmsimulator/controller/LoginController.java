package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.login.LoginRequestDTO;
import com.ogifmsim.fmsimulator.dto.login.LoginResponseDTO;
import com.ogifmsim.fmsimulator.model.user.User;
import com.ogifmsim.fmsimulator.model.user.UserRole;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/football/login")
public class LoginController { 
    private User user = new User("MrTousty", "2390", UserRole.ADMIN);

    Key key = Keys.hmacShaKeyFor("ogis_secret_key_ogis_secret_key_ogis_secret_key".getBytes(StandardCharsets.UTF_8));

    
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        if(loginRequestDTO.getUsername().equals(user.getUsername()) && loginRequestDTO.getPassword().equals(user.getPassword())) {
            String token = Jwts.builder() // TODO: Temporary token creaton
                               .setSubject(user.getUsername())
                               .setIssuedAt(new Date(System.currentTimeMillis()))
                               .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                               .signWith(key)
                               .compact();
            return ResponseEntity.ok(new LoginResponseDTO("Login successful", token, user));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Invalid credentials", null, null));
        }
    }
    
    
}
