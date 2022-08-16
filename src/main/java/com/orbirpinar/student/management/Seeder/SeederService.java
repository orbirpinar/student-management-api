package com.orbirpinar.student.management.Seeder;

import com.orbirpinar.student.management.Seeder.dto.SeederStudentResponse;
import com.orbirpinar.student.management.Seeder.dto.SeederTeacherRequest;
import com.orbirpinar.student.management.Seeder.dto.SeederTeacherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SeederService {

    @Qualifier("SeederRestTemplate")
    private final RestTemplate restTemplate;

    public SeederService(@Qualifier("SeederRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SeederTeacherResponse getTeachersBySchool(SeederTeacherRequest request) {
        ResponseEntity<SeederTeacherResponse> response = restTemplate
                .postForEntity("/teachers", request, SeederTeacherResponse.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error when seeding teachers");
        }
        return response.getBody();
    }

    public SeederStudentResponse getStudents() {
        ResponseEntity<SeederStudentResponse> response = restTemplate
                .getForEntity("/students", SeederStudentResponse.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error when seeding students");
        }
        return response.getBody();
    }
}
