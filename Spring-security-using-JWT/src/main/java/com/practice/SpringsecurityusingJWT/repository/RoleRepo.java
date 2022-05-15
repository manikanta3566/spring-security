package com.practice.SpringsecurityusingJWT.repository;

import com.practice.SpringsecurityusingJWT.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
}
