package at.technikum.studymasterbackend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");

        // OPTIONS-Anfragen erlauben
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK); // OPTIONS-Anfragen erlauben
            return;
        }
        // Ausnahme für den /users/register-Endpunkt
        String path = request.getRequestURI();
        if ("/api/users/register".equals(path) || "/users/register".equals(path)) { // Je nach Route
            response.setStatus(HttpServletResponse.SC_OK); // Anfragen erlauben
            return;
        }
        // Standardverhalten: 401 Unauthorized für alle anderen Anfragen
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"Unauthorized access\"}");
    }
}
