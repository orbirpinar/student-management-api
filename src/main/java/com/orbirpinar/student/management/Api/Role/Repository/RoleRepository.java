package com.orbirpinar.student.management.Api.Role.Repository;


import com.orbirpinar.student.management.Api.Role.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
