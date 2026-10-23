package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Repository.GradeRepo;
import com.template.Spring_Initializer.Repository.StudentRepo;
import com.template.Spring_Initializer.Repository.SubjectRepo;
import com.template.Spring_Initializer.Repository.TeacherRepo;

import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public GradeResponse addGrade(GradeRequest request) {

        Grade grade = new Grade();
        grade.setScore(request.score());
        grade.setGradeDate(request.gradeDate());
        grade.setStudent(
                studentRepo.findById(request.studentId()).orElseThrow(() -> new RuntimeException("Student not found")));
        grade.setSubject(
                subjectRepo.findById(request.subjectId()).orElseThrow(() -> new RuntimeException("Subject not found")));
        grade.setTeacher(
                teacherRepo.findById(request.teacherId()).orElseThrow(() -> new RuntimeException("Teacher not found")));

        Grade savedGrade = gradeRepo.save(grade);

        return toResponse(savedGrade);

    }

    @Transactional(readOnly = true)
    public List<GradeResponse> getAllGradeResponses() {
        return gradeRepo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public GradeResponse getGradeById(Long id) {

        Grade grade = gradeRepo.findById(id).orElseThrow(() -> new RuntimeException("Grade not found"));

        return toResponse(grade);

    }

    private GradeResponse toResponse(Grade grade) {

        return new GradeResponse(
                grade.getId(),
                grade.getScore(),
                grade.getGradeDate(),
                grade.getStudent().getId(),
                grade.getTeacher().getId(),
                grade.getSubject().getId());
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
