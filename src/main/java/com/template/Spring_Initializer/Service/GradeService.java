package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Model.Student;
import com.template.Spring_Initializer.Model.Subject;
import com.template.Spring_Initializer.Model.Teacher;
import com.template.Spring_Initializer.Repository.GradeRepo;
import com.template.Spring_Initializer.Repository.StudentRepo;
import com.template.Spring_Initializer.Repository.SubjectRepo;
import com.template.Spring_Initializer.Repository.TeacherRepo;

@Service
public class GradeService {

    private final GradeRepo gradeRepo;
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;
    private final TeacherRepo teacherRepo;

    
    public GradeService(
            GradeRepo gradeRepo,
            StudentRepo studentRepo,
            SubjectRepo subjectRepo,
            TeacherRepo teacherRepo) {

        this.gradeRepo = gradeRepo;
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.teacherRepo = teacherRepo;
    }

    public Grade addGrade(GradeRequest request) {
        Student student = studentRepo.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepo.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Teacher teacher = teacherRepo.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Grade grade = new Grade();
        grade.setScore(request.score());
        grade.setGradeDate(request.gradeDate());

        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setTeacher(teacher);
        Grade savedGrade = gradeRepo.save(grade);

        System.out.println("student: " + savedGrade.getStudent());
        System.out.println("subject: " + savedGrade.getSubject());
        System.out.println("teacher: " + savedGrade.getTeacher());

        return savedGrade;


    }


    public List<Grade> findAll() {
        return gradeRepo.findAll();
    }

    public Grade findById(Long id) {
        return gradeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No grade found"));
    }

    public void deleteById(Long id) {
        gradeRepo.deleteById(id);
    }

    public void deleteAll() {
        gradeRepo.deleteAll();
    }
}