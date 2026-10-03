package com.template.Spring_Initializer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Repository.GradeRepo;

@Service 
public class GradeService {
    
    private final GradeRepo gradeRepo;

    public GradeService(GradeRepo gradeRepo1 ) {

        this.gradeRepo = gradeRepo1;
    }

    public List<Grade> findAll() {
        return gradeRepo.findAll();
    }

    public Grade  addGrade( Grade entity) {
        return gradeRepo.save(entity);
    }

    public Grade findById(Long aLong) {
        return gradeRepo.findById(aLong).orElseThrow(()-> new RuntimeException("No grade found") );
    }

    // TODO I shall consider this an alternative for orElseThrow
    // public Optional<Grade> findById(Long id) {
    //     return gradeRepo.findById(id);
    // }




    public void deleteById(Long id) {
        gradeRepo.deleteById(id);
    }

    public void deleteAll() {
        gradeRepo.deleteAll();
    }

    

    
}
