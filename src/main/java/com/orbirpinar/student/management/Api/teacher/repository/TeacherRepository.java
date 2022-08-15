package com.orbirpinar.student.management.Api.teacher.repository;

import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
}
