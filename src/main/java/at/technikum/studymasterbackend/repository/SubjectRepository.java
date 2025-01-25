package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findBySemesterId(Long semesterId);

    List<Subject> findByUserId(Long userId);

    List<Subject> findBySemesterIdAndStatus(Long semesterId, String status); // Neue Methode für status

    List<Subject> findByAward(String award); // Neue Methode für Auszeichnung

}
