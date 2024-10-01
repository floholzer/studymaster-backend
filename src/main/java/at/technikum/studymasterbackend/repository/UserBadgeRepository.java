package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.UserBadge;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserBadgeRepository extends CrudRepository<UserBadge, Long> {
    List<UserBadge> findByUserId(Long userId);
}