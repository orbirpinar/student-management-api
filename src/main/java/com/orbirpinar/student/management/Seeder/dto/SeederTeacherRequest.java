package com.orbirpinar.student.management.Seeder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeederTeacherRequest {

    @JsonProperty("school_name")
    private String schoolName;
    @JsonProperty("school_website")
    private String schoolWebSite;
}
