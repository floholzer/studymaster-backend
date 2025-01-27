package at.technikum.studymasterbackend;

import at.technikum.studymasterbackend.model.Subject;
import at.technikum.studymasterbackend.model.Task;
import at.technikum.studymasterbackend.repository.SubjectRepository;
import at.technikum.studymasterbackend.repository.TaskRepository;
import at.technikum.studymasterbackend.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setName("SEPJ");
        subject.setSemesterId(1L);

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        // Act
        Subject createdSubject = subjectService.createSubject(subject);

        // Assert
        assertNotNull(createdSubject);
        assertEquals("SEPJ", createdSubject.getName());
        assertEquals(1L, createdSubject.getSemesterId());
        verify(subjectRepository, times(1)).save(subject);
    }

    @Test
    void testGetSubjectsBySemesterId() {
        // Arrange
        Long semesterId = 1L;
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("SEPJ");
        subject1.setSemesterId(semesterId);

        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("CLCO");
        subject2.setSemesterId(semesterId);

        List<Subject> subjects = Arrays.asList(subject1, subject2);

        when(subjectRepository.findBySemesterId(semesterId)).thenReturn(subjects);

        // Act
        List<Subject> result = subjectService.getSubjectsBySemesterId(semesterId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("SEPJ", result.get(0).getName());
        assertEquals("CLCO", result.get(1).getName());
        verify(subjectRepository, times(1)).findBySemesterId(semesterId);
    }

    @Test
    void testUpdateSubject() {
        // Arrange
        Long subjectId = 1L;
        Subject existingSubject = new Subject();
        existingSubject.setId(subjectId);
        existingSubject.setName("SEPJ");
        existingSubject.setSemesterId(1L);

        Subject updatedSubject = new Subject();
        updatedSubject.setName("SWRM");
        updatedSubject.setSemesterId(1L);

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(existingSubject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(updatedSubject);

        // Act
        Optional<Subject> result = subjectService.updateSubject(subjectId, updatedSubject);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("SWRM", result.get().getName());
        verify(subjectRepository, times(1)).findById(subjectId);
        verify(subjectRepository, times(1)).save(existingSubject);
    }
}