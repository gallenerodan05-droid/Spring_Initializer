package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.Model.Subject;
import com.template.Spring_Initializer.Repository.SubjectRepo;

@Service
public class SubjectService {

    private  final SubjectRepo subjectRepo;

    public SubjectService( SubjectRepo subjectRepo ) {
        this.subjectRepo = subjectRepo;
    }


    public  Subject   addSubject( Subject subject) {
        return subjectRepo.save(subject);
    }

    public List<Subject> findAll() {
        return subjectRepo.findAll();
    }


    public  Subject getSubject(Long id ){
      return   subjectRepo.findById(id).orElseThrow( ()-> new RuntimeException("Subject is not found") );
    }


    public void deleteAll() {
        subjectRepo.deleteAll();
    }

    public void deleteById(Long id) {
        subjectRepo.deleteById(id);
    }
}
