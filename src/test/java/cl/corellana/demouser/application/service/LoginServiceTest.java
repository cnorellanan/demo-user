package cl.corellana.demouser.application.service;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.out.GetUserPortOut;
import cl.corellana.demouser.application.port.out.SaveUserPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Mock
    private GetUserPortOut getUserPortOut;

    @Mock
    private SaveUserPortOut saveUserPortOut;

    @Mock
    private JWTUtils jwtUtils;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ValidToken_ReturnsUserResponseDTO() throws BusinessException {
        // Arrange
        String token = "validToken";
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        UserResponseDTO expectedResponse = new UserResponseDTO();
        expectedResponse.setEmail(email);

        when(jwtUtils.validateToken(token)).thenReturn(true);
        when(jwtUtils.extractEmail(token)).thenReturn(email);
        when(getUserPortOut.getUserByEmail(email)).thenReturn(userEntity);

        // Act
        UserResponseDTO actualResponse = loginService.execute(token);

        // Assert
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        verify(saveUserPortOut, times(1)).save(userEntity);
    }

    @Test
    public void testExecute_InvalidToken_ThrowsBusinessException() {
        // Arrange
        String token = "invalidToken";

        when(jwtUtils.validateToken(token)).thenReturn(false);

        // Act & Assert
        assertThrows(BusinessException.class, () -> loginService.execute(token));
    }

    @Test
    public void testExecute_UserNotFound_ThrowsBusinessException() {
        // Arrange
        String token = "validToken";
        String email = "test@example.com";

        when(jwtUtils.validateToken(token)).thenReturn(true);
        when(jwtUtils.extractEmail(token)).thenReturn(email);
        when(getUserPortOut.getUserByEmail(email)).thenReturn(null);

        // Act & Assert
        assertThrows(BusinessException.class, () -> loginService.execute(token));
    }


}