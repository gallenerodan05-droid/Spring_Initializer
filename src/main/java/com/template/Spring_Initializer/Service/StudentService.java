package com.template.Spring_Initializer.Service;


import com.template.Spring_Initializer.Model.Student;
import com.template.Spring_Initializer.Repository.StudentRepo;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student ){

        return  studentRepo.save(student);

    }

    public List<Student> getAllStudents( ){
        return  studentRepo.findAll();
    }

    public Student getStudent ( Long studentId ){
        return  studentRepo.findById(studentId).orElseThrow( () -> new RuntimeException("Student not found") )  ;
    }

    public void deleteStudent(Long studentId ){
        studentRepo.deleteById(studentId);
        System.out.println("Delete ka sakin boi hahah");
    }







}
