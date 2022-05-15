package com.practice.SpringsecurityusingJWT.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private String name;

    public Role(String name){
        this.name=name;
    }
}
