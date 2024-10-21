package at.technikum.studymasterbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtil {

    // Generiert einen geheimen Schlüssel mit HS256
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Extrahiert den Benutzernamen aus dem Token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Überprüft, ob das Token abgelaufen ist
    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Validiert das Token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generiert ein neues Token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token gültig für 1 Stunde
                .signWith(SECRET_KEY) // Signiert das Token mit dem geheimen Schlüssel
                .compact();
    }

    // Extrahiert alle Claims aus dem Token
    private Claims extractAllClaims(String token) {
        // Verwende parserBuilder statt parser für neuere JJWT-Versionen
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
}
