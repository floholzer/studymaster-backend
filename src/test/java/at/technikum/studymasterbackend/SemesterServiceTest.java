package at.technikum.studymasterbackend;

import at.technikum.studymasterbackend.model.Semester;
import at.technikum.studymasterbackend.repository.SemesterRepository;
import at.technikum.studymasterbackend.service.SemesterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SemesterServiceTest {

    @Mock
    private SemesterRepository semesterRepository;

    @InjectMocks
    private SemesterService semesterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSemester() {
        // Arrange
        Semester semester = new Semester();
        semester.setUserId(1L);
        semester.setName("WS2023");
        semester.setStatus("active");

        when(semesterRepository.countByUserId(1L)).thenReturn(0L);
        when(semesterRepository.save(any(Semester.class))).thenReturn(semester);

        // Act
        Semester createdSemester = semesterService.createSemester(semester);

        // Assert
        assertNotNull(createdSemester);
        assertEquals("WS2023", createdSemester.getName());
        assertEquals("active", createdSemester.getStatus());
        verify(semesterRepository, times(1)).countByUserId(1L);
        verify(semesterRepository, times(1)).save(semester);
    }

    @Test
    void testCreateSemester_ThrowsException_WhenMoreThan6Semesters() {
        // Arrange
        Semester semester = new Semester();
        semester.setUserId(1L);
        semester.setName("WS2023");
        semester.setStatus("active");

        when(semesterRepository.countByUserId(1L)).thenReturn(6L);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            semesterService.createSemester(semester);
        });

        assertEquals("A user cannot have more than 6 semesters.", exception.getMessage());
        verify(semesterRepository, times(1)).countByUserId(1L);
        verify(semesterRepository, never()).save(any(Semester.class));
    }
}