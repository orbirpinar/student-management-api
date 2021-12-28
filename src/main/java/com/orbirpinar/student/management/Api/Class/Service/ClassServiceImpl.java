package com.orbirpinar.student.management.Api.Class.Service;

import com.orbirpinar.student.management.Api.Class.Entity.Class;
import com.orbirpinar.student.management.Api.Class.Repository.ClassRepository;
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

    private final ClassRepository classRepository;
    private final StudentService studentService;

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

    @Override
    public Class getById(String id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Class.class,"classId",id));
    }

    @Override
    public Optional<Class> getByStudentId(String studentId) {
        Student student = studentService.getById(studentId);
        return classRepository.findByStudentsIn(Collections.singletonList(student));
    }

    @Override
    public Class create(Class _class) {
        return classRepository.save(_class);
    }

    @Override
    public Class update(Class _class, String id) {
        Class existingClass = getById(id);
        NullAwareBeanUtils.copyNonNullProperties(_class,existingClass);
        return classRepository.save(existingClass);
    }

    @Override
    public Class saveOrUpdateStudentsClass(Class _class, String studentId) {
        Student student = studentService.getById(studentId);
        student.set_class(_class);
        studentService.save(student);
        return student.get_class();
    }
}
