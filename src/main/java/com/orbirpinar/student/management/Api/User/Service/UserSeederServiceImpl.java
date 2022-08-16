package com.orbirpinar.student.management.Api.User.Service;

import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Repository.UserRepository;
import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import com.orbirpinar.student.management.Api.teacher.repository.TeacherRepository;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakUserService;
import com.orbirpinar.student.management.Keycloak.dto.KeycloakUserCreateDto;
import com.orbirpinar.student.management.Seeder.SeederService;
import com.orbirpinar.student.management.Seeder.dto.SeederTeacherRequest;
import com.orbirpinar.student.management.Seeder.dto.SeederTeacherResponse;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class UserSeederServiceImpl implements UserSeederService {

    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;
    private final SeederService seederService;


    @Override
    @Transactional
    public void importUser(SeederTeacherRequest request) {

        SeederTeacherResponse response = seederService.getTeachersBySchool(request);
        response.getData().forEach(res -> {
            String firstNameAndLastNAme = res.getFirstname() + "." + res.getLastname();
            String username = firstNameAndLastNAme.replace(" ", ".");
            String email = username + "@email.com";
            KeycloakUserCreateDto keycloakUserCreateDto = new KeycloakUserCreateDto();
            keycloakUserCreateDto.setFirstName(res.getFirstname());
            keycloakUserCreateDto.setLastName(res.getLastname());
            keycloakUserCreateDto.setEmail(email);
            keycloakUserCreateDto.setUsername(username);
            keycloakUserService.createOrUpdateUser(keycloakUserCreateDto);
            List<UserViewDto> userViewDtos = keycloakUserService.getByUsername(username);
            UserViewDto userViewDto = userViewDtos.get(0);
            User user = Transformer.map(userViewDto, User.class);
            User savedUser = userRepository.save(user);
            Teacher teacher = new Teacher();
            teacher.setUser(savedUser);
            teacher.setDepartment(res.getDepartment());
            teacherRepository.save(teacher);
        });
    }
}
