package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Badge;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<Badge, Long> {
}
