package com.template.Spring_Initializer.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Model.Grade;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(source = "gradeDate", target = "date")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "subject.id", target = "subjectId")
    GradeResponse toResponse(Grade grade);

    List<GradeResponse> toResponseList(List<Grade> grades);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    Grade toEntity(GradeRequest request);
}

