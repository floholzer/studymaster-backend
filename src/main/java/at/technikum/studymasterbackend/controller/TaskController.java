package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.PointsPerCompletion;
import at.technikum.studymasterbackend.model.Task;
import at.technikum.studymasterbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Retrieve all tasks for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.ok(newTask);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Optional<Task> task = taskService.updateTask(id, updatedTask);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Access granted");
    }

    // Mark a task as completed and award points
    @PostMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId, @RequestBody Task requestTask) {
        Optional<Task> task = taskService.markTaskAsCompleted(taskId, requestTask.getPointsEarned());
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}
