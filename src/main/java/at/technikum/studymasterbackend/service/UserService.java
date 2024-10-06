package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.User;
import at.technikum.studymasterbackend.model.UserSession;
import at.technikum.studymasterbackend.repository.UserRepository;
import at.technikum.studymasterbackend.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private UserSessionRepository userSessionRepository;

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

    public String createSession(User user) {
        String sessionId = UUID.randomUUID().toString();
        UserSession session = new UserSession();
        session.setSession_id(sessionId);
        session.setUser_id(user.getId());
        session.setCreation_time(new Date());
        session.setExpiry_time(new Date(System.currentTimeMillis() + (1000 * 60 * 60)));  // 1-hour expiry

        userSessionRepository.save(session);
        return sessionId;
    }

    // Benutzer löschen
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Benutzer anhand des Benutzernamens finden
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
