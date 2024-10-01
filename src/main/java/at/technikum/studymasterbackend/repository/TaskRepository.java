package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}