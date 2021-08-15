package com.orbirpinar.student.management.Api.User.Entity;



import com.orbirpinar.student.management.Api.Role.Entity.Role;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class User extends BaseEntity {


    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Boolean emailVerified;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;




}
