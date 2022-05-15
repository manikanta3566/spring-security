package com.practice.springsecurityusingbasicauth.repository;

import com.practice.springsecurityusingbasicauth.entity.Role;
import com.practice.springsecurityusingbasicauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

}
