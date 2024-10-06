package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.User;
import at.technikum.studymasterbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Registrierung eines neuen Benutzers
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Benutzeranmeldung
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = userService.findUserByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Add session handling logic
            String sessionId = userService.createSession(user.get());
            return ResponseEntity.ok(sessionId);  // return session ID
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Alle Benutzer abrufen
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}