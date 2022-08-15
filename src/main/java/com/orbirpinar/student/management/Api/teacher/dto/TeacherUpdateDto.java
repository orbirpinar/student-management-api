package com.orbirpinar.student.management.Api.teacher.dto;

import com.orbirpinar.student.management.Api.common.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherUpdateDto {

    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String graduateSchool;
}
