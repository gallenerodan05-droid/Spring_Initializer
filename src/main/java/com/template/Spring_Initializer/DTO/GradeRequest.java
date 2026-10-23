package com.template.Spring_Initializer.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record  GradeRequest (

    @NotNull (message="Score is required!" )
    @DecimalMin(value= "0.0" , message= "Score must not be negative")
    @DecimalMax (value = "100.0", message =" Must not exceed the value of 100 " )
    Double score,
     
    @NotNull (message=" Must not be empty!")
    @PastOrPresent (message= "Grade cannot be in the future")
    LocalDate gradeDate,

    @NotNull (message= "Student ID is required")
    @Positive (message = "Student ID must be positive" )
    Long studentId,
     
    @NotNull (message= "SubjectID is required")
    @Positive (message = "Subject ID must be positive" )
    Long subjectId,

    @NotNull (message= "Teacher ID is required")
    @Positive (message = "Teacher ID must be positive" )
    Long teacherId

){}