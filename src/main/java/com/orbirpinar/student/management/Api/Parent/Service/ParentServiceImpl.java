package com.orbirpinar.student.management.Api.Parent.Service;

import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
import com.orbirpinar.student.management.Api.Parent.Repository.ParentRepository;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Student.Service.StudentService;
import com.orbirpinar.student.management.Exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {

    private final StudentService studentService;
    private final ParentRepository parentRepository;

    public ParentServiceImpl(StudentService studentService, ParentRepository parentRepository) {
        this.studentService = studentService;
        this.parentRepository = parentRepository;
    }

    @Override
    public List<Parent> getAll() {
        return parentRepository.findAll();
    }

    @Override
    public Parent getById(String parentId) {
        return parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException(Parent.class,"parentId",parentId));
    }

    @Override
    public List<Parent> createParentByStudent(String studentId, Parent parent) {
        Student student = studentService.getById(studentId);
        student.getParent().add(parent);
        studentService.save(student);
        return student.getParent();
    }

    @Override
    public List<Parent> getParentByStudent(String studentId) {
        Student student = studentService.getById(studentId);
        return parentRepository.findParentsByStudent(student);
    }
}
