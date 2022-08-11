package com.orbirpinar.student.management.Api.Student.Controller;

import com.orbirpinar.student.management.Api.Class.DTO.ClassRoomCreateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Class.Service.ClassService;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/students")
@AllArgsConstructor
public class StudentClassController {

    private final ClassService classService;

    @PutMapping(value = "/{studentId}/classes")
    public ResponseEntity<ClassViewDto> createOrUpdate(@RequestBody ClassRoomCreateDto classTobeCreated,
                                                         @PathVariable String studentId) {
        ClassRoom _class = Transformer.map(classTobeCreated, ClassRoom.class);
        ClassRoom newClass = classService.saveOrUpdateStudentsClass(_class, studentId);
        return ResponseEntity.ok(Transformer.map(newClass, ClassViewDto.class));
    }
}
