package com.orbirpinar.student.management.Seeder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SeederTeacherResponse {

    public List<SeederTeacherData> data;

    @Getter
    @Setter
    public static class SeederTeacherData {

        private String department;
        private String firstname;
        private String lastname;
    }

}
