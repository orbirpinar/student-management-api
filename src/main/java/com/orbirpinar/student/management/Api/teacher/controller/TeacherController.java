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
                teachers.stream().map(Teacher::toViewDto).toList()
        );
    }

    @PostMapping
    public ResponseEntity<TeacherViewDto> create(@RequestBody TeacherCreateDto teacherCreateDto) {
        Teacher teacher = Transformer.map(teacherCreateDto, Teacher.class);
        Teacher newTeacher = teacherService.create(teacher,teacherCreateDto.getUserId());
        return ResponseEntity.ok(
               newTeacher.toViewDto()
        );
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherViewDto> update(@RequestBody TeacherUpdateDto teacherUpdateDto,
                                                 @PathVariable String teacherId) {
        Teacher teacher = Transformer.map(teacherUpdateDto,Teacher.class);
        Teacher updatedTeacher = teacherService.update(teacherId,teacher);
        return ResponseEntity.ok(
               updatedTeacher.toViewDto()
        );
    }

    @DeleteMapping("/{teacherId}")
    public void  delete(@PathVariable String id) {
        teacherService.deleteById(id);
    }

}
