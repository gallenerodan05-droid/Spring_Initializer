package com.template.Spring_Initializer.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Service.GradeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grades", description = "Endpoints for managing student grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a new grade", description = "Creates a grade record for a given student, teacher, and subject")
    @ApiResponse(responseCode = "201", description = "Grade created successfully")
    @ApiResponse(responseCode = "400", description = "Validation failed")
    @ApiResponse(responseCode = "404", description = "Student, teacher, or subject not found")
    public GradeResponse addGrade(@Valid  @RequestBody GradeRequest request) {
        return gradeService.addGrade(request);
    }

    @GetMapping
    @Operation(summary = "Get all grades", description = "Retrieves a list of all recorded grades")
    public List<GradeResponse> findAll() {
        return gradeService.getAllGradeResponses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find grade by ID", description = "Retrieves details of a specific grade by its database ID")
    @ApiResponse(responseCode = "200", description = "Grade found")
    @ApiResponse(responseCode = "404", description = "Grade not found")
    public GradeResponse findById(@PathVariable Long id) {
        return gradeService.getGradeById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete grade by ID", description = "Removes a grade record by its ID")
    public void deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete all grades", description = "Removes all grade records")
    public void deleteAll() {
        gradeService.deleteAll();
    }
}