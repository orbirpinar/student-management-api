package com.orbirpinar.student.management.Api.teacher.service;

import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<Teacher> getAll();

    Teacher getById(String id);
    Teacher create(Teacher teacher,String userId);

    Teacher update(String id, Teacher teacher);

    void deleteById(String id);



}
