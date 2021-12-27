package com.orbirpinar.student.management.Api.Student.DTO;

import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class StudentCreateDto implements Serializable {

    private String id;

    private String firstname;

    private String lastname;

    private String gender;

    private LocalDate birthDate;

    private String phoneNumber;

    private ClassViewDto _class;
}
