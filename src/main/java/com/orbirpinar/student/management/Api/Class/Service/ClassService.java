package com.orbirpinar.student.management.Api.Class.Service;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<ClassRoom> getAll();
    ClassRoom getById(String id);
    Optional<ClassRoom> getByStudentId(String studentId);
    ClassRoom create(ClassRoom _class);
    ClassRoom update(ClassRoom _class, String id);
    ClassRoom saveOrUpdateStudentsClass(ClassRoom _class, String studentId);
}
