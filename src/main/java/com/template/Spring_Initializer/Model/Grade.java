package com.template.Spring_Initializer.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private  Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private  Teacher teacher;

    @ManyToOne
    @JoinColumn (name = "student_id")
    private  Student student;

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
