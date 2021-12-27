package com.orbirpinar.student.management.Api.Class.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassUpdateDto implements Serializable {

    private String id;

    private String grade;

    private String branch;
}
