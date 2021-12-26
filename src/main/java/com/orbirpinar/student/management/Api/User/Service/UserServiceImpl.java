package com.orbirpinar.student.management.Api.User.Service;


import com.orbirpinar.student.management.Api.Role.DTO.RoleViewDto;
import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Repository.UserRepository;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakUserService;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    private final KeycloakUserService keycloakUserService;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User updatedUser) {
        User user = this.getById(id);
        NullAwareBeanUtils.copyNonNullProperties(updatedUser,user);
        return userRepository.save(user);
    }

    @Override
    public List<RoleViewDto> getUsersRoles(String id) throws Exception {
        return keycloakUserService.getUserRoles(id);
    }

}
