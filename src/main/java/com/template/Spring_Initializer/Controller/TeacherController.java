package com.template.Spring_Initializer.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.Spring_Initializer.Model.Teacher;
import com.template.Spring_Initializer.Service.TeacherService;

@RestController 
@RequestMapping ("/api/teacher")
public class TeacherController {
     

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    
    @GetMapping 
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }

    @PostMapping 
    public Teacher addTeacher(@RequestBody  Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }



    @GetMapping ("/{id}")
    public Teacher findById(@PathVariable  Long id) {
        return teacherService.findById(id);
    }

    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    

    

    

}
