package at.technikum.studymasterbackend.repository;

import org.springframework.data.repository.CrudRepository;
import at.technikum.studymasterbackend.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

