package com.orbirpinar.student.management.Api.Parent.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParentViewDto implements Serializable {

    private String id;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String phone;
    private String job;
    private String parentType;
}
