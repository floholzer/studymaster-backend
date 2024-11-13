package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    List<Semester> findByUserId(Long userId);
}
