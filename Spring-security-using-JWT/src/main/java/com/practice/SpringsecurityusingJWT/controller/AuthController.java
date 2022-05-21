package com.practice.SpringsecurityusingJWT.controller;

import com.practice.SpringsecurityusingJWT.dto.AuthRequest;
import com.practice.SpringsecurityusingJWT.dto.AuthResponse;
import com.practice.SpringsecurityusingJWT.jwt.JwtHelper;
import com.practice.SpringsecurityusingJWT.services.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
private JwtHelper jwtHelper;

@Autowired
private UserServiceImpl userService;

@PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
jwtHelper.authenticateUser(authRequest.getUserName(),authRequest.getPassword());
    UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
    String token = jwtHelper.generateToken(userDetails);
    return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
}



}
