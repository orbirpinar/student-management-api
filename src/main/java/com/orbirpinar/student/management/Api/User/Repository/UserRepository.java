package com.orbirpinar.student.management.Api.User.Repository;

import com.orbirpinar.student.management.Api.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByUsername(String username);
}
