package com.orbirpinar.student.management.Seeder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SeederStudentResponse {

    public List<SeederStudentData> data;

    @Getter
    @Setter
    static class SeederStudentData {

        private String firstname;
        private String lastname;
        private String branch;
        private String level;
        @JsonProperty("school_number")
        private String schoolNumber;
    }
}
