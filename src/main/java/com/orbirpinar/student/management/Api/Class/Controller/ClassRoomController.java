package com.orbirpinar.student.management.Api.Class.Controller;

import com.orbirpinar.student.management.Api.Class.DTO.ClassRoomCreateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassUpdateDto;
import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Class.Service.ClassService;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassRoomController {

    private final ClassService classService;

    @GetMapping
    public ResponseEntity<List<ClassViewDto>> getAll() {
        List<ClassRoom> classes = classService.getAll();
        return ResponseEntity.ok(Transformer.mapAll(classes, ClassViewDto.class));
    }

    @GetMapping(value = "/{classRoomId}")
    public ResponseEntity<ClassViewDto> getById(@PathVariable String classRoomId) {
        ClassRoom _class = classService.getById(classRoomId);
        return ResponseEntity.ok(Transformer.map(_class,ClassViewDto.class));
    }

    @PostMapping
    public ResponseEntity<ClassViewDto> create(@RequestBody ClassRoomCreateDto classToBeCreated) {
        ClassRoom _class = classService.create(Transformer.map(classToBeCreated, ClassRoom.class));
        return ResponseEntity.ok(Transformer.map(_class,ClassViewDto.class));
    }

    @PutMapping(value = "/{classRoomId}")
    public ResponseEntity<ClassViewDto> update(@PathVariable String classRoomId, @RequestBody ClassUpdateDto _class) {
        ClassRoom classToBeUpdated = Transformer.map(_class, ClassRoom.class);
        ClassRoom updatedClass = classService.update(classToBeUpdated, classRoomId);
        return ResponseEntity.ok(Transformer.map(updatedClass,ClassViewDto.class));
    }



}
