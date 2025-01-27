package at.technikum.studymasterbackend;

import at.technikum.studymasterbackend.model.Task;
import at.technikum.studymasterbackend.repository.TaskRepository;
import at.technikum.studymasterbackend.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        // Arrange
        Task task = new Task();
        task.setUserId(1L);
        task.setTitle("Aufgabe 1");
        task.setDescription("Beschreibung der Aufgabe");
        task.setStatus("open");
        task.setEcts(BigDecimal.valueOf(5));
        task.setCreatedAt(LocalDateTime.now());

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals("Aufgabe 1", createdTask.getTitle());
        assertEquals("open", createdTask.getStatus());
        assertEquals(BigDecimal.valueOf(5), createdTask.getEcts());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testCreateTask_DefaultValues() {
        // Arrange
        Task task = new Task();
        task.setUserId(1L);
        task.setTitle("Aufgabe 1");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals("open", createdTask.getStatus()); // Standardwert für Status
        assertEquals(BigDecimal.ZERO, createdTask.getEcts()); // Standardwert für ECTS
        assertNotNull(createdTask.getCreatedAt()); // Erstellungsdatum sollte gesetzt sein
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTasksByUserId() {
        // Arrange
        Long userId = 1L;
        Task task1 = new Task();
        task1.setId(1L);
        task1.setUserId(userId);
        task1.setTitle("Aufgabe 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setUserId(userId);
        task2.setTitle("Aufgabe 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findByUserId(userId)).thenReturn(tasks);

        // Act
        List<Task> result = taskService.getTasksByUserId(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Aufgabe 1", result.get(0).getTitle());
        assertEquals("Aufgabe 2", result.get(1).getTitle());
        verify(taskRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetTasksByUserId_ThrowsException_WhenUserIdIsNull() {
        // Arrange
        Long userId = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.getTasksByUserId(userId);
        });

        assertEquals("userId is null", exception.getMessage());
        verify(taskRepository, never()).findByUserId(any());
    }
}