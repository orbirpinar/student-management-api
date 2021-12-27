package com.orbirpinar.student.management.Api.Class.Repository;

import com.orbirpinar.student.management.Api.Class.Entity.Class;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class,String> {

        List<Class> findByStudent(Student student);

}
