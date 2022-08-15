package com.orbirpinar.student.management.Api.Role.Entity;


import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends BaseEntity {

    @Id
    private String id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> user = new HashSet<User>();
}
