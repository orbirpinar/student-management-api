package com.orbirpinar.student.management.Api.Parent.Repository;

import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent,String> {

        List<Parent> findParentsByStudent(Student student);
}
