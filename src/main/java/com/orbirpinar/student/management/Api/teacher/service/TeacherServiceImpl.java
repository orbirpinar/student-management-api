package com.orbirpinar.student.management.Api.teacher.service;

import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Repository.UserRepository;
import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import com.orbirpinar.student.management.Api.teacher.repository.TeacherRepository;
import com.orbirpinar.student.management.Exception.EntityNotFoundException;
import com.orbirpinar.student.management.Exception.Error;
import com.orbirpinar.student.management.Exception.StudentManagementBusinessException;
import com.orbirpinar.student.management.Utils.NullAwareBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(String id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Teacher.class, id));
    }

    @Override
    public Teacher create(Teacher teacher,String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new StudentManagementBusinessException(Error.USER_NOT_FOUND);
        }
        teacher.setUser(user.get());
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(String id, Teacher teacher) {
        Teacher existingTeacher = getById(id);
        NullAwareBeanUtils.copyNonNullProperties(teacher,existingTeacher);
        return teacherRepository.save(existingTeacher);
    }

    @Override
    public void deleteById(String id) {
        Teacher teacher = getById(id);
        teacherRepository.delete(teacher);
    }
}
