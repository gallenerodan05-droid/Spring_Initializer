package com.template.Spring_Initializer.Controller;

import com.template.Spring_Initializer.Model.Subject;
import com.template.Spring_Initializer.Service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private  final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}" )
    public Subject getSubject(@PathVariable Long id) {
        return subjectService.getSubject(id);
    }

    @DeleteMapping
    public void deleteAll() {
        subjectService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        subjectService.deleteById(id);
    }
}
