package com.practice.springsecurityusingbasicauth;

import com.practice.springsecurityusingbasicauth.entity.Role;
import com.practice.springsecurityusingbasicauth.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringSecurityUsingBasicAuthApplication {

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUsingBasicAuthApplication.class, args);
	}

	@PostConstruct
	public void createRole(){
		Role role1=new Role();
		role1.setId("101");
		role1.setName("ROLE_USER");
		roleRepo.save(role1);
		Role role2=new Role();
		role2.setId("102");
		role2.setName("ROLE_ADMIN");
		roleRepo.save(role2);

	}
}
