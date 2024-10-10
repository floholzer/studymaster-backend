package at.technikum.studymasterbackend.model;

public class AuthRequest {
    private String username;
    private String password;

    // Standardkonstruktor für JSON Parsing
    public AuthRequest() {}

    // Getter und Setter

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
