package at.technikum.studymasterbackend.model;

public class AuthResponse {
    private final String jwt;
    private final Long userId;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;

    public AuthResponse(String jwt, Long userId, String username, String email, String firstName, String lastName) {
        this.jwt = jwt;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter
    public String getJwt() { return jwt; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}

