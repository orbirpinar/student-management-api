package com.orbirpinar.student.management.Api.Class.Service;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Class.Repository.ClassRoomRepository;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Student.Service.StudentService;
import com.orbirpinar.student.management.Exception.EntityNotFoundException;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassServiceImpl implements ClassService{

    private final ClassRoomRepository classRoomRepository;
    private final StudentService studentService;

    @Override
    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom getById(String id) {
        return classRoomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ClassRoom.class,"classId",id));
    }

    @Override
    public Optional<ClassRoom> getByStudentId(String studentId) {
        Student student = studentService.getById(studentId);
        return classRoomRepository.findByStudentsIn(Collections.singletonList(student));
    }

    @Override
    public ClassRoom create(ClassRoom _class) {
        return classRoomRepository.save(_class);
    }

    @Override
    public ClassRoom update(ClassRoom _class, String id) {
        ClassRoom existingClass = getById(id);
        NullAwareBeanUtils.copyNonNullProperties(_class,existingClass);
        return classRoomRepository.save(existingClass);
    }

    @Override
    public ClassRoom saveOrUpdateStudentsClass(ClassRoom _class, String studentId) {
        Student student = studentService.getById(studentId);
        student.setClassRoom(_class);
        studentService.create(student);
        return student.getClassRoom();
    }
}
