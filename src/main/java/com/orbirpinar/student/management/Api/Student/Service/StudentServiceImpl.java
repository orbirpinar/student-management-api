package com.orbirpinar.student.management.Api.Student.Service;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Class.Repository.ClassRoomRepository;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Student.Repository.StudentRepository;
import com.orbirpinar.student.management.Exception.Error;
import com.orbirpinar.student.management.Exception.StudentManagementBusinessException;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.orbirpinar.student.management.Exception.Error.*;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ClassRoomRepository classRoomRepository) {
        this.studentRepository = studentRepository;
        this.classRoomRepository = classRoomRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Student create(Student student) {

        String grade = student.getClassRoom().getGrade();
        String branch = student.getClassRoom().getBranch();
        ClassRoom classRoom = classRoomRepository.findByGradeAndBranch(grade, branch)
                .orElseThrow(() -> new StudentManagementBusinessException(CLASS_NOT_FOUND));
        student.setClassRoom(classRoom);
        return studentRepository.save(student);
    }

    @Override
    public Student update(String id, Student requestedStudent) {
        Student student = this.getById(id);
        NullAwareBeanUtils.copyNonNullProperties(requestedStudent,student);
        return studentRepository.save(student);
    }

    @Override
    public void delete(String id) {
        Student student = this.getById(id);
        studentRepository.delete(student);
    }
}
