package com.template.Spring_Initializer.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.template.Spring_Initializer.Model.Subject;
import com.template.Spring_Initializer.Repository.SubjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepo subjectRepo;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject;

    @BeforeEach
    void setUp() {
        subject = new Subject("Physics");
        subject.setId(1L);
    }

    @Test
    void addSubject_savesAndReturnsSubject_whenCalled() {
        when(subjectRepo.save(subject)).thenReturn(subject);

        Subject savedSubject = subjectService.addSubject(subject);

        assertNotNull(savedSubject);
        assertEquals(1L, savedSubject.getId());
        assertEquals("Physics", savedSubject.getName());

        verify(subjectRepo).save(subject);
    }

    @Test
    void findAll_returnsSubjectList_whenSubjectsExist() {
        Subject secondSubject = new Subject("Chemistry");
        secondSubject.setId(2L);

        when(subjectRepo.findAll()).thenReturn(List.of(subject, secondSubject));

        List<Subject> subjects = subjectService.findAll();

        assertEquals(2, subjects.size());
        assertEquals(subject, subjects.get(0));
        assertEquals(secondSubject, subjects.get(1));

        verify(subjectRepo).findAll();
    }

    @Test
    void findAll_returnsEmptyList_whenNoSubjectsExist() {
        when(subjectRepo.findAll()).thenReturn(Collections.emptyList());

        List<Subject> subjects = subjectService.findAll();

        assertNotNull(subjects);
        assertTrue(subjects.isEmpty());

        verify(subjectRepo).findAll();
    }

    @Test
    void getSubject_returnsSubject_whenSubjectExists() {
        when(subjectRepo.findById(1L)).thenReturn(Optional.of(subject));

        Subject foundSubject = subjectService.getSubject(1L);

        assertNotNull(foundSubject);
        assertEquals(1L, foundSubject.getId());
        assertEquals("Physics", foundSubject.getName());

        verify(subjectRepo).findById(1L);
    }

    @Test
    void getSubject_throwsRuntimeException_whenSubjectDoesNotExist() {
        when(subjectRepo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> subjectService.getSubject(99L)
        );
        assertEquals("Subject is not found", exception.getMessage());

        verify(subjectRepo).findById(99L);
    }

    @Test
    void deleteById_callsRepositoryDeleteById_whenIdProvided() {
        subjectService.deleteById(1L);

        verify(subjectRepo).deleteById(1L);
    }

    @Test
    void deleteAll_callsRepositoryDeleteAll_whenCalled() {
        subjectService.deleteAll();

        verify(subjectRepo).deleteAll();
    }
}

