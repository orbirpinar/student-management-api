package com.orbirpinar.student.management.Api.Student.Controller;

import com.orbirpinar.student.management.Api.Class.DTO.ClassCreateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import com.orbirpinar.student.management.Api.Class.Entity.Class;
import com.orbirpinar.student.management.Api.Class.Service.ClassService;
import com.orbirpinar.student.management.Api.Student.DTO.StudentViewDto;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/students")
@AllArgsConstructor
public class StudentClassController {

    private final ClassService classService;

    // TODO SAVE OR UPDATE STUDENT CLASS
    @PutMapping(value = "/{studentId}/classes")
    public ResponseEntity<ClassViewDto> createOrUpdate(@RequestBody ClassCreateDto classTobeCreated,
                                                         @PathVariable String studentId) {
        Class _class = Transformer.map(classTobeCreated, Class.class);
        Class newClass = classService.saveOrUpdateStudentsClass(_class, studentId);
        return ResponseEntity.ok(Transformer.map(newClass, ClassViewDto.class));
    }
}
