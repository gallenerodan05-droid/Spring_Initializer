package com.template.Spring_Initializer.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.template.Spring_Initializer.Exception.ResourceNotFoundException;
import com.template.Spring_Initializer.Model.Student;
import com.template.Spring_Initializer.Repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Alice", "Johnson");
        ReflectionTestUtils.setField(student, "id", 1L);
    }

    @Test
    void addStudent_savesAndReturnsStudent_whenCalled() {
        when(studentRepo.save(student)).thenReturn(student);

        Student savedStudent = studentService.addStudent(student);

        assertNotNull(savedStudent);
        assertEquals(1L, savedStudent.getId());
        assertEquals("Alice", savedStudent.getFirstname());
        assertEquals("Johnson", savedStudent.getLastname());

        verify(studentRepo).save(student);
    }

    @Test
    void getAllStudents_returnsStudentList_whenStudentsExist() {
        Student secondStudent = new Student("Bob", "Williams");
        ReflectionTestUtils.setField(secondStudent, "id", 2L);

        when(studentRepo.findAll()).thenReturn(List.of(student, secondStudent));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        assertEquals(student, students.get(0));
        assertEquals(secondStudent, students.get(1));

        verify(studentRepo).findAll();
    }

    @Test
    void getAllStudents_returnsEmptyList_whenNoStudentsExist() {
        when(studentRepo.findAll()).thenReturn(Collections.emptyList());

        List<Student> students = studentService.getAllStudents();

        assertNotNull(students);
        assertTrue(students.isEmpty());

        verify(studentRepo).findAll();
    }

    @Test
    void getStudent_returnsStudent_whenStudentExists() {
        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudent(1L);

        assertNotNull(foundStudent);
        assertEquals(1L, foundStudent.getId());
        assertEquals("Alice", foundStudent.getFirstname());
        assertEquals("Johnson", foundStudent.getLastname());

        verify(studentRepo).findById(1L);
    }

    @Test
    void getStudent_throwsResourceNotFoundException_whenStudentDoesNotExist() {
        when(studentRepo.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> studentService.getStudent(99L)
        );
        assertEquals("Student not found", exception.getMessage());

        verify(studentRepo).findById(99L);
    }

    @Test
    void deleteStudent_callsRepositoryDeleteById_whenIdProvided() {
        studentService.deleteStudent(1L);

        verify(studentRepo).deleteById(1L);
    }
}

