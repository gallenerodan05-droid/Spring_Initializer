package com.template.Spring_Initializer.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Service.GradeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grades")
public class GradeController {



    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public GradeResponse addGrade(@Valid  @RequestBody GradeRequest request) {
        return gradeService.addGrade(request);
    }

    @GetMapping
    public List<GradeResponse> findAll() {
        return gradeService.getAllGradeResponses();
    }

    @GetMapping("/{id}")
    public Grade findById(@PathVariable Long id) {
        return gradeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        gradeService.deleteAll();
    }
}