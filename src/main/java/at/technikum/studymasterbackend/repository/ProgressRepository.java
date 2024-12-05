package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Progress;
import org.springframework.data.repository.CrudRepository;

public interface ProgressRepository extends CrudRepository<Progress, Long> {
    Progress getProgressByUserId(Long userId);
}
