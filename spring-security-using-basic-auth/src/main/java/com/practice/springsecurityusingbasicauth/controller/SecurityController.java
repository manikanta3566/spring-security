package com.practice.springsecurityusingbasicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class SecurityController {

    @GetMapping("/login")
    public String login(Principal principal){
        String name = principal.getName();
        return "welcome "+name;
    }

    @GetMapping("/dashboard")
    public String userDashboard(){
        return "welcome to dashboard";
    }

    @GetMapping("/adminsettings")
    public String adminSettings(){
        return "welcome admin to admin settings";
    }
}
