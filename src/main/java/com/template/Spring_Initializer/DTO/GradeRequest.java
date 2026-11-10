package com.template.Spring_Initializer.DTO;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request body for creating a new grade record")
public record  GradeRequest (

    @Schema(description = "Grade score from 0.0 to 100.0", example = "95.0")
    @NotNull (message="Score is required!" )
    @DecimalMin(value= "0.0" , message= "Score must not be negative")
    @DecimalMax (value = "100.0", message =" Must not exceed the value of 100 " )
    Double score,
     
    @Schema(description = "Date when grade was recorded", example = "2026-01-15")
    @NotNull (message=" Must not be empty!")
    @PastOrPresent (message= "Grade cannot be in the future")
    LocalDate gradeDate,

    @Schema(description = "ID of the student", example = "1")
    @NotNull (message= "Student ID is required")
    @Positive (message = "Student ID must be positive" )
    Long studentId,
     
    @Schema(description = "ID of the subject", example = "2")
    @NotNull (message= "SubjectID is required")
    @Positive (message = "Subject ID must be positive" )
    Long subjectId,

    @Schema(description = "ID of the teacher", example = "3")
    @NotNull (message= "Teacher ID is required")
    @Positive (message = "Teacher ID must be positive" )
    Long teacherId

){}