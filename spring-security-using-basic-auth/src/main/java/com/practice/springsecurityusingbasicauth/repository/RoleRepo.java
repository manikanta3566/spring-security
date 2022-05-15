package com.practice.springsecurityusingbasicauth.repository;

import com.practice.springsecurityusingbasicauth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
}
