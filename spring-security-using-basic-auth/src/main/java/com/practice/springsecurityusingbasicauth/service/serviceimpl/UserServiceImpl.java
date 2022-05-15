package com.practice.springsecurityusingbasicauth.service.serviceimpl;

import com.practice.springsecurityusingbasicauth.entity.Role;
import com.practice.springsecurityusingbasicauth.entity.User;
import com.practice.springsecurityusingbasicauth.repository.RoleRepo;
import com.practice.springsecurityusingbasicauth.repository.UserRepo;
import com.practice.springsecurityusingbasicauth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findById("101").orElseThrow(() -> new RuntimeException("role not found"));
        user1.getRoles().add(role);
        return userRepo.save(user1);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found" + userId));
    }

    @Override
    public User updateUser(User user, String userId, String roleId) {
        User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found" + userId));

        if (roleId != null) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("role not found" + roleId));
            user1.getRoles().add(role);
        }
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return userRepo.save(user1);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found" + id));
        if(user.getEmail().equalsIgnoreCase("admin@gmail.com")){
            throw new RuntimeException("user is "+user.getName());
        }
        user.setDeleted(true);
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("user not found by username "+email);
        }
        Set<Role> roles = user.getRoles();
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .accountExpired(false)
                .authorities(roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()))
                .accountLocked(false)
                .build();
    }
}
