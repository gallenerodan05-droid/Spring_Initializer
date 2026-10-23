package com.template.Spring_Initializer.DTO;

import java.time.LocalDate;

public record  GradeResponse(
    
    Long id,
    Double score,
    LocalDate date,
    Long studentId,
    Long teacherId,
    Long subjectId

)  

{}
