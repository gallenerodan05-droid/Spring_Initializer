package com.template.Spring_Initializer.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.template.Spring_Initializer.DTO.GradeRequest;
import com.template.Spring_Initializer.DTO.GradeResponse;
import com.template.Spring_Initializer.Exception.ResourceNotFoundException;
import com.template.Spring_Initializer.Model.Grade;
import com.template.Spring_Initializer.Model.Student;
import com.template.Spring_Initializer.Model.Subject;
import com.template.Spring_Initializer.Model.Teacher;
import com.template.Spring_Initializer.Repository.GradeRepo;
import com.template.Spring_Initializer.Repository.StudentRepo;
import com.template.Spring_Initializer.Repository.SubjectRepo;
import com.template.Spring_Initializer.Repository.TeacherRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @Mock
    private GradeRepo gradeRepo;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private SubjectRepo subjectRepo;

    @Mock
    private TeacherRepo teacherRepo;

    @InjectMocks
    private GradeService gradeService;

    private Student student;
    private Subject subject;
    private Teacher teacher;
    private Grade grade;

    @BeforeEach
    void setUp() {
        student = new Student("John", "Doe");
        ReflectionTestUtils.setField(student, "id", 1L);

        subject = new Subject("Mathematics");
        ReflectionTestUtils.setField(subject, "id", 2L);

        teacher = new Teacher("Jane", "Smith");
        ReflectionTestUtils.setField(teacher, "id", 3L);

        grade = new Grade(95.0, LocalDate.of(2026, 1, 15));
        ReflectionTestUtils.setField(grade, "id", 10L);
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setTeacher(teacher);
    }

    @Test
    void addGrade_savesAndReturnsGradeResponse_whenStudentSubjectAndTeacherExist() {
        GradeRequest request = new GradeRequest(
                95.0,
                LocalDate.of(2026, 1, 15),
                1L,
                2L,
                3L
        );

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepo.findById(2L)).thenReturn(Optional.of(subject));
        when(teacherRepo.findById(3L)).thenReturn(Optional.of(teacher));
        when(gradeRepo.save(any(Grade.class))).thenReturn(grade);

        GradeResponse response = gradeService.addGrade(request);

        assertNotNull(response);
        assertEquals(10L, response.id());
        assertEquals(95.0, response.score());
        assertEquals(LocalDate.of(2026, 1, 15), response.date());
        assertEquals(1L, response.studentId());
        assertEquals(3L, response.teacherId());
        assertEquals(2L, response.subjectId());

        verify(studentRepo).findById(1L);
        verify(subjectRepo).findById(2L);
        verify(teacherRepo).findById(3L);

        ArgumentCaptor<Grade> gradeCaptor = ArgumentCaptor.forClass(Grade.class);
        verify(gradeRepo).save(gradeCaptor.capture());

        Grade gradeToSave = gradeCaptor.getValue();
        assertEquals(95.0, gradeToSave.getScore());
        assertEquals(LocalDate.of(2026, 1, 15), gradeToSave.getGradeDate());
        assertEquals(student, gradeToSave.getStudent());
        assertEquals(subject, gradeToSave.getSubject());
        assertEquals(teacher, gradeToSave.getTeacher());
    }

    @Test
    void addGrade_throwsResourceNotFoundException_whenStudentDoesNotExist() {
        GradeRequest request = new GradeRequest(
                95.0,
                LocalDate.of(2026, 1, 15),
                1L,
                2L,
                3L
        );

        when(studentRepo.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gradeService.addGrade(request)
        );
        assertEquals("Student not found", exception.getMessage());

        verify(studentRepo).findById(1L);
        verify(subjectRepo, never()).findById(any());
        verify(teacherRepo, never()).findById(any());
        verify(gradeRepo, never()).save(any());
    }

    @Test
    void addGrade_throwsResourceNotFoundException_whenSubjectDoesNotExist() {
        GradeRequest request = new GradeRequest(
                95.0,
                LocalDate.of(2026, 1, 15),
                1L,
                2L,
                3L
        );

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepo.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gradeService.addGrade(request)
        );
        assertEquals("Subject not found", exception.getMessage());

        verify(studentRepo).findById(1L);
        verify(subjectRepo).findById(2L);
        verify(teacherRepo, never()).findById(any());
        verify(gradeRepo, never()).save(any());
    }

    @Test
    void addGrade_throwsResourceNotFoundException_whenTeacherDoesNotExist() {
        GradeRequest request = new GradeRequest(
                95.0,
                LocalDate.of(2026, 1, 15),
                1L,
                2L,
                3L
        );

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepo.findById(2L)).thenReturn(Optional.of(subject));
        when(teacherRepo.findById(3L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gradeService.addGrade(request)
        );
        assertEquals("Teacher not found", exception.getMessage());

        verify(studentRepo).findById(1L);
        verify(subjectRepo).findById(2L);
        verify(teacherRepo).findById(3L);
        verify(gradeRepo, never()).save(any());
    }

    @Test
    void getAllGradeResponses_returnsMappedGradeResponses_whenGradesExist() {
        when(gradeRepo.findAll()).thenReturn(List.of(grade));

        List<GradeResponse> responses = gradeService.getAllGradeResponses();

        assertEquals(1, responses.size());
        GradeResponse response = responses.getFirst();
        assertEquals(10L, response.id());
        assertEquals(95.0, response.score());
        assertEquals(LocalDate.of(2026, 1, 15), response.date());
        assertEquals(1L, response.studentId());
        assertEquals(3L, response.teacherId());
        assertEquals(2L, response.subjectId());

        verify(gradeRepo).findAll();
    }

    @Test
    void getAllGradeResponses_returnsEmptyList_whenNoGradesExist() {
        when(gradeRepo.findAll()).thenReturn(Collections.emptyList());

        List<GradeResponse> responses = gradeService.getAllGradeResponses();

        assertNotNull(responses);
        assertTrue(responses.isEmpty());

        verify(gradeRepo).findAll();
    }

    @Test
    void getGradeById_returnsGradeResponse_whenIdExists() {
        when(gradeRepo.findById(10L)).thenReturn(Optional.of(grade));

        GradeResponse response = gradeService.getGradeById(10L);

        assertNotNull(response);
        assertEquals(10L, response.id());
        assertEquals(95.0, response.score());
        assertEquals(LocalDate.of(2026, 1, 15), response.date());
        assertEquals(1L, response.studentId());
        assertEquals(3L, response.teacherId());
        assertEquals(2L, response.subjectId());

        verify(gradeRepo).findById(10L);
    }

    @Test
    void getGradeById_throwsResourceNotFoundException_whenIdDoesNotExist() {
        when(gradeRepo.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gradeService.getGradeById(99L)
        );
        assertEquals("Grade with ID 99 not found", exception.getMessage());

        verify(gradeRepo).findById(99L);
    }

    @Test
    void findAll_returnsGradeList_whenGradesExist() {
        when(gradeRepo.findAll()).thenReturn(List.of(grade));

        List<Grade> grades = gradeService.findAll();

        assertEquals(1, grades.size());
        assertEquals(grade, grades.getFirst());

        verify(gradeRepo).findAll();
    }

    @Test
    void findAll_returnsEmptyList_whenNoGradesExist() {
        when(gradeRepo.findAll()).thenReturn(Collections.emptyList());

        List<Grade> grades = gradeService.findAll();

        assertNotNull(grades);
        assertTrue(grades.isEmpty());

        verify(gradeRepo).findAll();
    }

    @Test
    void findById_returnsGrade_whenIdExists() {
        when(gradeRepo.findById(10L)).thenReturn(Optional.of(grade));

        Grade result = gradeService.findById(10L);

        assertNotNull(result);
        assertEquals(grade, result);

        verify(gradeRepo).findById(10L);
    }

    @Test
    void findById_throwsResourceNotFoundException_whenIdDoesNotExist() {
        when(gradeRepo.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gradeService.findById(99L)
        );
        assertEquals("Grade with ID 99 not found", exception.getMessage());

        verify(gradeRepo).findById(99L);
    }

    @Test
    void deleteById_callsRepositoryDeleteById_whenIdProvided() {
        gradeService.deleteById(10L);

        verify(gradeRepo).deleteById(10L);
    }

    @Test
    void deleteAll_callsRepositoryDeleteAll_whenCalled() {
        gradeService.deleteAll();

        verify(gradeRepo).deleteAll();
    }
}
