package com.orbirpinar.student.management.Api.teacher.dto;

import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.common.Gender;
import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherCreateDto {

    @NotNull
    private String userId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String graduateSchool;
    private String department;

    public Teacher toEntity(TeacherCreateDto teacherCreateDto) {
        Teacher teacher = new Teacher();
        teacher.setBirthDate(teacherCreateDto.getBirthDate());
        teacher.setGraduatedSchool(teacherCreateDto.getGraduateSchool());
        teacher.setGender(teacherCreateDto.getGender());
        teacher.setDepartment(teacherCreateDto.getDepartment());
        User user = new User();
        user.setId(teacherCreateDto.getUserId());
        teacher.setUser(user);
        return teacher;
    }
}
