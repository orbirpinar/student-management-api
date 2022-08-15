package com.orbirpinar.student.management.Api.teacher.controller;

import com.orbirpinar.student.management.Api.teacher.dto.TeacherCreateDto;
import com.orbirpinar.student.management.Api.teacher.dto.TeacherUpdateDto;
import com.orbirpinar.student.management.Api.teacher.dto.TeacherViewDto;
import com.orbirpinar.student.management.Api.teacher.entity.Teacher;
import com.orbirpinar.student.management.Api.teacher.service.TeacherService;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherViewDto>> getAll() {
        List<Teacher> teachers = teacherService.getAll();
        return ResponseEntity.ok(
                Transformer.mapAll(teachers,TeacherViewDto.class)
        );
    }

    @PostMapping
    public ResponseEntity<TeacherViewDto> create(@RequestBody TeacherCreateDto teacherCreateDto) {
        Teacher teacher = Transformer.map(teacherCreateDto, Teacher.class);
        Teacher newTeacher = teacherService.create(teacher,teacherCreateDto.getUserId());
        return ResponseEntity.ok(
                Transformer.map(newTeacher,TeacherViewDto.class)
        );
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherViewDto> update(@RequestBody TeacherUpdateDto teacherUpdateDto,
                                                 @PathVariable String teacherId) {
        Teacher teacher = Transformer.map(teacherUpdateDto,Teacher.class);
        Teacher updatedTeacher = teacherService.update(teacherId,teacher);
        return ResponseEntity.ok(
                Transformer.map(updatedTeacher,TeacherViewDto.class)
        );
    }

    @DeleteMapping("/{teacherId}")
    public void  delete(@PathVariable String id) {
        teacherService.deleteById(id);
    }

}
