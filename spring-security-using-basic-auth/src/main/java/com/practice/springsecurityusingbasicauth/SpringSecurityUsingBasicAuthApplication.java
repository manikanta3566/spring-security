package com.practice.springsecurityusingbasicauth;

import com.practice.springsecurityusingbasicauth.entity.Role;
import com.practice.springsecurityusingbasicauth.entity.User;
import com.practice.springsecurityusingbasicauth.repository.RoleRepo;
import com.practice.springsecurityusingbasicauth.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@Log4j2
public class SpringSecurityUsingBasicAuthApplication {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityUsingBasicAuthApplication.class, args);
    }


    @PostConstruct
    public void createRole() {
        try {
            Role role1 = new Role();
            role1.setId("101");
            role1.setName("ROLE_USER");
            roleRepo.save(role1);
            Role role2 = new Role();
            role2.setId("102");
            role2.setName("ROLE_ADMIN");
            roleRepo.save(role2);
        } catch (Exception e) {
            log.error("error {}", e.getMessage());
        }
    }
	@PostConstruct
	public void createUser() {
		try {
            User byEmail = userRepo.findByEmail("admin@gmail.com");
            if(byEmail==null) {
                User userAdmin = new User();
                userAdmin.setEmail("admin@gmail.com");
                userAdmin.setName("admin");
                userAdmin.setPassword(passwordEncoder.encode("admin"));
                Role roleAdmin = roleRepo.findById("102").get();
                userAdmin.getRoles().add(roleAdmin);
                userRepo.save(userAdmin);
            }else{
                log.info("user admin present in db {}", byEmail.getEmail());
            }
		} catch (Exception e) {
			log.error("error {}", e.getMessage());
		}
	}
}

