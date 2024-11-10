package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.User;
import at.technikum.studymasterbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Alle Benutzer abrufen
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Benutzer nach ID abrufen
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Benutzer registrieren
    public User registerUser(User user) {
        // Überschreibe is_admin, um sicherzustellen, dass neue Benutzer keine Admins sind
        user.setIs_admin(false);
        return userRepository.save(user);
    }

    // Benutzeranmeldung
    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    // Benutzer löschen
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Benutzer anhand des Benutzernamens finden
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Benutzer speichern
    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
