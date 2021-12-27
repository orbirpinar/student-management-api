package com.orbirpinar.student.management.Api.Class.Controller;

import com.orbirpinar.student.management.Api.Class.DTO.ClassCreateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassUpdateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import com.orbirpinar.student.management.Api.Class.Entity.Class;
import com.orbirpinar.student.management.Api.Class.Service.ClassService;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping
    public ResponseEntity<List<ClassViewDto>> getAll() {
        List<Class> classes = classService.getAll();
        return ResponseEntity.ok(Transformer.mapAll(classes, ClassViewDto.class));
    }

    @GetMapping(value = "/{classId}")
    public ResponseEntity<ClassViewDto> getById(@PathVariable String classId) {
        Class _class = classService.getById(classId);
        return ResponseEntity.ok(Transformer.map(_class,ClassViewDto.class));
    }

    public ResponseEntity<ClassViewDto> create(@RequestBody ClassCreateDto classToBeCreated) {
        Class _class = classService.create(Transformer.map(classToBeCreated, Class.class));
        return ResponseEntity.ok(Transformer.map(_class,ClassViewDto.class));
    }

    public ResponseEntity<ClassViewDto> update(@PathVariable String classId, @RequestBody ClassUpdateDto _class) {
        Class classToBeUpdated = Transformer.map(_class,Class.class);
        Class updatedClass = classService.update(classToBeUpdated, classId);
        return ResponseEntity.ok(Transformer.map(updatedClass,ClassViewDto.class));
    }



}
