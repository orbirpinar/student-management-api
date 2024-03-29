package com.orbirpinar.student.management.Api.User.Controller;



import com.orbirpinar.student.management.Api.Role.DTO.RoleViewDto;
import com.orbirpinar.student.management.Api.User.DTO.UserCreateDto;
import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Service.UserSeederService;
import com.orbirpinar.student.management.Api.User.Service.UserService;
import com.orbirpinar.student.management.Seeder.dto.SeederTeacherRequest;
import com.orbirpinar.student.management.Utils.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;
    private final UserSeederService userImportService;

    public UserController(UserService userService, UserSeederService userImportService) {
        this.userService = userService;
        this.userImportService = userImportService;
    }

    @GetMapping
    public ResponseEntity<List<UserViewDto>> getAll() {
        List<UserViewDto> userList = Transformer.mapAll(userService.getAll(), UserViewDto.class);
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserViewDto> getById(@PathVariable String userId) {
        UserViewDto userList = Transformer.map(userService.getById(userId), UserViewDto.class);
        return ResponseEntity.ok(userList);
    }


    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserViewDto> update(@PathVariable String userId,@RequestBody UserCreateDto userCreateDto) {
        User user = Transformer.map(userCreateDto,User.class);
        UserViewDto newUser = Transformer.map(userService.update(userId,user), UserViewDto.class);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping(value = "/{userId}/roles")
    public ResponseEntity<List<RoleViewDto>> getUsersRoles(@PathVariable String userId) throws Exception {
        List<RoleViewDto> usersRoles = userService.getUsersRoles(userId);
        return ResponseEntity.ok(usersRoles);
    }

    @PostMapping("/import-teacher")
    public void importUser(@RequestBody  SeederTeacherRequest request) {
        userImportService.importUser(request);
    }


}
