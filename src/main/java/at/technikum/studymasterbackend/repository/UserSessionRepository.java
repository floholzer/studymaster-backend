package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
