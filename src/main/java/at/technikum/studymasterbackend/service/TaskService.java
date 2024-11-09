package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Task;
import at.technikum.studymasterbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Retrieve all tasks for a user
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // Create a new task
    public Task createTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus("open");
        }
        if (task.getEcts() == null) {
            task.setEcts(BigDecimal.ZERO);
        }

        Task savedTask = taskRepository.save(task);
        if (savedTask.getCreatedAt() == null) {
            savedTask.setCreatedAt(LocalDateTime.now()); // If needed for immediate response
        }
        return savedTask;
    }



    // Update an existing task
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setEcts(updatedTask.getEcts());
            task.setPointsPerSubmission(updatedTask.getPointsPerSubmission());
            task.setTotalSubmissions(updatedTask.getTotalSubmissions());
            task.setCompletedSubmissions(updatedTask.getCompletedSubmissions());
            return taskRepository.save(task);
        });
    }

    // Delete a task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Mark a task as completed and assign points
    public Optional<Task> markTaskAsCompleted(Long id, int pointsPerCompletion) {
        return taskRepository.findById(id).map(task -> {
            task.setStatus("completed");
            task.setPointsEarned(task.getPointsEarned() + pointsPerCompletion);
            return taskRepository.save(task);
        });
    }
}
