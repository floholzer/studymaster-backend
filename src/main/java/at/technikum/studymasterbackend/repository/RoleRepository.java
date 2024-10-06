package at.technikum.studymasterbackend.repository;

import at.technikum.studymasterbackend.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
