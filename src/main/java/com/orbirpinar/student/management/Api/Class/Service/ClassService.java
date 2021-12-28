package com.orbirpinar.student.management.Api.Class.Service;

import com.orbirpinar.student.management.Api.Class.Entity.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<Class> getAll();
    Class getById(String id);
    Optional<Class> getByStudentId(String studentId);
    Class create(Class _class);
    Class update(Class _class,String id);
    Class saveOrUpdateStudentsClass(Class _class, String studentId);
}
