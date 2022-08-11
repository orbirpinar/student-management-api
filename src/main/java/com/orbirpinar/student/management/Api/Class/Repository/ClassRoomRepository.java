package com.orbirpinar.student.management.Api.Class.Repository;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom,String> {

        Optional<ClassRoom> findByStudentsIn(List<Student> student);

        @Query("SELECT c FROM ClassRoom c WHERE  c.grade = :grade AND c.branch = :branch")
        Optional<ClassRoom> findByGradeAndBranch(String grade,String branch);

}
