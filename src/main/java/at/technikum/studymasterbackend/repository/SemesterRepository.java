package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    List<Semester> findByUserId(Long userId);
    List<Semester> findByUserIdAndStatus(Long userId, String status); // Neue Methode mit status
    long countByUserId(Long userId); // notwendig damit ein Benutzer nicht mehr als 6 Semester haben kann
}

