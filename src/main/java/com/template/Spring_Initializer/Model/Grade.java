package com.template.Spring_Initializer.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional= false )
    @JoinColumn(name = "subject_id", nullable= false )
    private  Subject subject;

    @ManyToOne( optional= false)
    @JoinColumn(name = "teacher_id", nullable= false)
    private  Teacher teacher;

    @ManyToOne (optional= false)
    @JoinColumn (name = "student_id" ,nullable= false)
    private  Student student;

    @Column(nullable=false)
    private Double score;

    
    private LocalDate gradeDate;
    public Grade() {
    }

    public Grade(Double score, LocalDate gradeDate) {
        this.score = score;
        this.gradeDate = gradeDate;
    }

    public Long getId() {
        return id;
    }



    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDate getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(LocalDate gradeDate) {
        this.gradeDate = gradeDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
