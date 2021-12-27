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
                Transformer.mapAll(students, StudentViewDto.class)
        );
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentViewDto> getById(@PathVariable String studentId) {
        Student student = studentService.getById(studentId);
        return ResponseEntity.ok(Transformer.map(student, StudentViewDto.class));


    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity<StudentViewDto> update(@PathVariable String studentId, @RequestBody
            StudentUpdateDto requestedStudentDto) {
        Student requestedStudent = Transformer.map(requestedStudentDto, Student.class);
        Student student = studentService.update(studentId, requestedStudent);
        return ResponseEntity.ok(
                Transformer.map(student, StudentViewDto.class)
        );
    }

    @PostMapping
    public ResponseEntity<StudentViewDto> create(@RequestBody StudentCreateDto requestedStudentDto) {
        Student requestedStudent = Transformer.map(requestedStudentDto, Student.class);
        Student student = studentService.save(requestedStudent);
        return ResponseEntity.ok(
                Transformer.map(student, StudentViewDto.class)
        );
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable String studentId) {
        studentService.delete(studentId);
    }

}
