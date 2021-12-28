package com.orbirpinar.student.management.Api.Student.Controller;

import com.orbirpinar.student.management.Api.Parent.DTO.ParentCreateDto;
import com.orbirpinar.student.management.Api.Parent.DTO.ParentViewDto;
import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
import com.orbirpinar.student.management.Api.Parent.Service.ParentService;
import com.orbirpinar.student.management.Utils.Transformer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
public class StudentParentController {

    private final ParentService parentService;

    public StudentParentController(ParentService parentService) {
        this.parentService = parentService;
    }


    @PostMapping(value = "/{studentId}/parents")
    public ResponseEntity<List<ParentViewDto>> addParentToStudent(@PathVariable String studentId, @RequestBody ParentCreateDto parentCreateDto) {
        Parent parent = Transformer.map(parentCreateDto, Parent.class);
        List<Parent> parentByStudent = parentService.createParentByStudent(studentId, parent);
        return ResponseEntity.ok(Transformer.mapAll(parentByStudent,ParentViewDto.class));
    }

    @GetMapping(value = "/{studentId}/parents")
    public ResponseEntity<List<ParentViewDto>> getParentsByStudent(@PathVariable String studentId) {
        List<Parent> parentList = parentService.getParentByStudent(studentId);
        return ResponseEntity.ok(Transformer.mapAll(parentList,ParentViewDto.class));
    }
}
