package com.template.Spring_Initializer.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Exception.ResourceNotFoundException;
import com.template.Spring_Initializer.Mapper.GradeMapper;
import com.template.Spring_Initializer.Model.Grade;
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
    private final GradeMapper gradeMapper;

    public GradeService(
            GradeRepo gradeRepo,
            StudentRepo studentRepo,
            SubjectRepo subjectRepo,
            TeacherRepo teacherRepo,
            GradeMapper gradeMapper) {

        this.gradeRepo = gradeRepo;
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.teacherRepo = teacherRepo;
        this.gradeMapper = gradeMapper;
    }

    @Transactional
    public GradeResponse addGrade(GradeRequest request) {

        Grade grade = gradeMapper.toEntity(request);
        grade.setStudent(
                studentRepo.findById(request.studentId()).orElseThrow(() -> new ResourceNotFoundException("Student not found")));
        grade.setSubject(
                subjectRepo.findById(request.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found")));
        grade.setTeacher(
                teacherRepo.findById(request.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found")));

        Grade savedGrade = gradeRepo.save(grade);

        return gradeMapper.toResponse(savedGrade);

    }

    @Transactional(readOnly = true)
    public List<GradeResponse> getAllGradeResponses() {
        return gradeMapper.toResponseList(gradeRepo.findAll());
    }

    @Transactional(readOnly = true)
    public GradeResponse getGradeById(Long id) {

        Grade grade = gradeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grade with ID " + id + " not found"));

        return gradeMapper.toResponse(grade);

    }

    public List<Grade> findAll() {
        return gradeRepo.findAll();
    }

    public Grade findById(Long id) {
        return gradeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade with ID " + id + " not found"));
    }

    public void deleteById(Long id) {
        gradeRepo.deleteById(id);
    }

    public void deleteAll() {
        gradeRepo.deleteAll();
    }
}
