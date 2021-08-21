package com.orbirpinar.student.management.Api.Student.Service;

import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Student.Repository.StudentRepository;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
