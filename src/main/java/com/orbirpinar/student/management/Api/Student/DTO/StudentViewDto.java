package com.orbirpinar.student.management.Api.Student.DTO;

import com.orbirpinar.student.management.Api.Student.Entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class StudentViewDto implements Serializable {

    private String id;

    private String name;

    private String firstname;

    private String lastname;


    private Gender gender;

    private LocalDate birthDate;

    private String phoneNumber;

    private String classRoom;


}
