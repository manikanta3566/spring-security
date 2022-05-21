package com.practice.SpringsecurityusingJWT.controller;

import com.practice.SpringsecurityusingJWT.entity.User;
import com.practice.SpringsecurityusingJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello" ;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getallusers")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userid}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userid") String id, @RequestParam(required = false) String roleid) {
        return new ResponseEntity<>(userService.updateUser(user, id, roleid), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userid}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("userid") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted with id " + id, HttpStatus.ACCEPTED);
    }
}
