package com.practice.SpringsecurityusingJWT.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    @Column(name = "deleted")
    private boolean isDeleted=false;
    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID",referencedColumnName = "id")
    )
    private Set<Role> roles=new HashSet<>();

    public User(){
        this.id= UUID.randomUUID().toString();
    }
}
