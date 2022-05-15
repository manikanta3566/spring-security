package com.practice.springsecurityusingbasicauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Role {
    @Id
    private String id;
    private String name;
}
