package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Repository.GradeRepo;

@Service 
public class GradeService {
    
    private final GradeRepo gradeRepo;

    public GradeService(GradeRepo gradeRepo1 ) {

        this.gradeRepo = gradeRepo1;
    }

    public Grade addGrade(Grade grade ){
        return gradeRepo.save(grade);
    }

    public List<Grade> findAll() {
        return gradeRepo.findAll();
    }
// TODO I shall consider this an alternative for orElseThrow
    // public Optional<Grade> findById(Long id) {
    //     return gradeRepo.findById(id);
    // }


    public Grade findById(Long id) {
        return gradeRepo.findById(id).orElseThrow(()-> new RuntimeException("No grade found") );
    }

    public void deleteById(Long id) {
        gradeRepo.deleteById(id);
    }

    public void deleteAll() {
        gradeRepo.deleteAll();
    }

    

    
}
