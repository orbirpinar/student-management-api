package com.orbirpinar.student.management.Api.User.Service;

import com.orbirpinar.student.management.Seeder.dto.SeederTeacherRequest;
import org.springframework.stereotype.Service;


@Service
public interface UserSeederService {

   void importUser(SeederTeacherRequest request);
}
