package com.orbirpinar.student.management.Api.User.Service;

import com.orbirpinar.student.management.Api.Role.DTO.RoleViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll();
    User getById(String id);
    User create(User user);
    User update(String id,User user);
    List<RoleViewDto> getUsersRoles(String id) throws Exception;

}
