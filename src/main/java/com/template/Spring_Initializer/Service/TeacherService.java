package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.Model.Teacher;
import com.template.Spring_Initializer.Repository.TeacherRepo;

@Service
public class TeacherService {
    private  final TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public void deleteAll() {
        teacherRepo.deleteAll();
    }

    public Teacher findById(Long aLong) {
        return teacherRepo.findById(aLong).orElseThrow(()-> new RuntimeException("Teacher is not found") ) ;
    }

    public void deleteById(Long aLong) {
        teacherRepo.deleteById(aLong);
    }

    public  Teacher  addTeacher( Teacher teacher) {
        return teacherRepo.save(teacher);
    }


    public List<Teacher> findAll() {
        return teacherRepo.findAll();
    }
}
