package com.orbirpinar.student.management.Api.Student.Controller;

import com.orbirpinar.student.management.Api.Student.DTO.StudentCreateDto;
import com.orbirpinar.student.management.Api.Student.DTO.StudentUpdateDto;
import com.orbirpinar.student.management.Api.Student.DTO.StudentViewDto;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Student.Service.StudentService;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentViewDto>> getAll() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(
                students.stream().map(Student::toViewDto).toList()
        );
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentViewDto> getById(@PathVariable String studentId) {
        Student student = studentService.getById(studentId);
        return ResponseEntity.ok(student.toViewDto());


    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity<StudentViewDto> update(@PathVariable String studentId, @RequestBody
    StudentUpdateDto requestedStudentDto) {
        Student requestedStudent = Transformer.map(requestedStudentDto, Student.class);
        Student student = studentService.update(studentId, requestedStudent);
        return ResponseEntity.ok(
                student.toViewDto()
        );
    }

    @PostMapping
    public ResponseEntity<StudentViewDto> create(@RequestBody StudentCreateDto requestedStudentDto) {
        Student requestedStudent = StudentCreateDto.toEntity(requestedStudentDto);
        Student student = studentService.create(requestedStudent);
        return ResponseEntity.ok(
                student.toViewDto()
        );
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable String studentId) {
        studentService.delete(studentId);
    }

}
