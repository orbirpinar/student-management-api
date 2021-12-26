package com.orbirpinar.student.management.Api.Parent.Controller;

import com.orbirpinar.student.management.Api.Parent.DTO.ParentViewDto;
import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
import com.orbirpinar.student.management.Api.Parent.Service.ParentService;
import com.orbirpinar.student.management.Utils.Transformer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {


    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }


    @GetMapping
    public ResponseEntity<List<ParentViewDto>> getAll() {
        List<Parent> parentList = parentService.getAll();
        return ResponseEntity.ok(Transformer.mapAll(parentList,ParentViewDto.class));
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<ParentViewDto> getById(@PathVariable String parentId) {
        Parent parent = parentService.getById(parentId);
        return ResponseEntity.ok(Transformer.map(parent,ParentViewDto.class));
    }
}
