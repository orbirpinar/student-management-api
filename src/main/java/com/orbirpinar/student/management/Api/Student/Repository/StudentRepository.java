package com.orbirpinar.student.management.Api.Student.Repository;

import com.orbirpinar.student.management.Api.Student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
}
