package com.orbirpinar.student.management.Api.Student.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequestDto {

    private String id;

    private String name;

    private String firstname;

    private String lastname;


    private String gender;

    private LocalDate birthDate;

    private String phoneNumber;
}
