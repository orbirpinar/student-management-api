package com.orbirpinar.student.management.Api.Student.Service;

import com.orbirpinar.student.management.Api.Student.Entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();
    Student getById(String id);
    Student create(Student student);
    Student update(String id, Student student);
    void delete(String id);
}
