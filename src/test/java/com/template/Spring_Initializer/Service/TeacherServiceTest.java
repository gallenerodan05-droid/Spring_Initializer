package com.template.Spring_Initializer.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.template.Spring_Initializer.Model.Teacher;
import com.template.Spring_Initializer.Repository.TeacherRepo;
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
class TeacherServiceTest {

    @Mock
    private TeacherRepo teacherRepo;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher("Robert", "Davis");
        ReflectionTestUtils.setField(teacher, "id", 1L);
    }

    @Test
    void addTeacher_savesAndReturnsTeacher_whenCalled() {
        when(teacherRepo.save(teacher)).thenReturn(teacher);

        Teacher savedTeacher = teacherService.addTeacher(teacher);

        assertNotNull(savedTeacher);
        assertEquals(1L, savedTeacher.getId());
        assertEquals("Robert", savedTeacher.getFirstname());
        assertEquals("Davis", savedTeacher.getLastname());

        verify(teacherRepo).save(teacher);
    }

    @Test
    void findAll_returnsTeacherList_whenTeachersExist() {
        Teacher secondTeacher = new Teacher("Emily", "Clark");
        ReflectionTestUtils.setField(secondTeacher, "id", 2L);

        when(teacherRepo.findAll()).thenReturn(List.of(teacher, secondTeacher));

        List<Teacher> teachers = teacherService.findAll();

        assertEquals(2, teachers.size());
        assertEquals(teacher, teachers.get(0));
        assertEquals(secondTeacher, teachers.get(1));

        verify(teacherRepo).findAll();
    }

    @Test
    void findAll_returnsEmptyList_whenNoTeachersExist() {
        when(teacherRepo.findAll()).thenReturn(Collections.emptyList());

        List<Teacher> teachers = teacherService.findAll();

        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());

        verify(teacherRepo).findAll();
    }

    @Test
    void findById_returnsTeacher_whenTeacherExists() {
        when(teacherRepo.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher foundTeacher = teacherService.findById(1L);

        assertNotNull(foundTeacher);
        assertEquals(1L, foundTeacher.getId());
        assertEquals("Robert", foundTeacher.getFirstname());
        assertEquals("Davis", foundTeacher.getLastname());

        verify(teacherRepo).findById(1L);
    }

    @Test
    void findById_throwsRuntimeException_whenTeacherDoesNotExist() {
        when(teacherRepo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> teacherService.findById(99L)
        );
        assertEquals("Teacher is not found", exception.getMessage());

        verify(teacherRepo).findById(99L);
    }

    @Test
    void deleteById_callsRepositoryDeleteById_whenIdProvided() {
        teacherService.deleteById(1L);

        verify(teacherRepo).deleteById(1L);
    }

    @Test
    void deleteAll_callsRepositoryDeleteAll_whenCalled() {
        teacherService.deleteAll();

        verify(teacherRepo).deleteAll();
    }
}

