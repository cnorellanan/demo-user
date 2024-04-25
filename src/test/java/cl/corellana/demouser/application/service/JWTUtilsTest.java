package cl.corellana.demouser.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JWTUtilsTest {

    @InjectMocks
    private JWTUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtUtils.expirationTime = 9000;
        jwtUtils.secretKey = "Y25vcmVsbGFuYW5AZ21haWwuY29t";
    }

    @Test
    public void testExtractEmail_ValidToken_ReturnsEmail() {
        // Arrange
        String token = validToken();
        Claims claims = new DefaultClaims();
        claims.setSubject("test@example.com");

        // Act
        String email = jwtUtils.extractEmail(token);

        // Assert
        assertEquals("test@example.com", email);
    }

    @Test
    public void testExtractExpiration_ValidToken_ReturnsExpirationDate() {
        // Arrange
        String token = validToken();
        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date());

        // Act
        Date expiration = jwtUtils.extractExpiration(token);

        // Assert
        assertNotNull(expiration);
    }

    @Test
    public void testGenerateToken_ValidUsername_ReturnsToken() {
        // Arrange
        String username = "test@example.com";

        // Act
        String token = jwtUtils.generateToken(username);

        // Assert
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    public void testIsTokenExpired_NotExpired_ReturnsFalse() {
        // Arrange
        String token = validToken();
        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date(System.currentTimeMillis() + 1000));

        // Act
        boolean expired = jwtUtils.isTokenExpired(token);

        // Assert
        assertFalse(expired);
    }

    @Test
    public void testValidateToken_NotExpired_ReturnsTrue() {
        // Arrange
        String token = validToken();
        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date(System.currentTimeMillis() + 1000));

        // Act
        boolean valid = jwtUtils.validateToken(token);

        // Assert
        assertTrue(valid);
    }

    private String validToken() {
        return jwtUtils.generateToken("test@example.com");
    }

}
