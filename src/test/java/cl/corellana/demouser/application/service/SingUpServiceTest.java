package cl.corellana.demouser.application.service;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;
import cl.corellana.demouser.adapters.web.dto.PhoneDTO;
import cl.corellana.demouser.adapters.web.dto.UserDTO;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.out.GetUserPortOut;
import cl.corellana.demouser.application.port.out.SaveUserPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class SingUpServiceTest {

    @Mock
    private SaveUserPortOut saveUserPortOut;

    @Mock
    private GetUserPortOut getUserPortOut;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private SingUpService signUpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_UserDoesNotExist_Success() throws BusinessException {
        // Given
        UserDTO user = new UserDTO();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("Password123");
        PhoneDTO phone = new PhoneDTO();
        phone.setNumber(123456789);
        phone.setCityCode(1);
        phone.setContryCode("+1");
        user.setPhones(new PhoneDTO[]{phone});

        when(getUserPortOut.getUserByEmail(anyString())).thenReturn(null);

        // When
        UserResponseDTO response = signUpService.execute(user);

        // Then
        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertEquals("johndoe@example.com", response.getEmail());
    }

    @Test
    void testExecute_UserAlreadyExists_ExceptionThrown() {
        UserDTO user = new UserDTO();
        user.setName("Jane Smith");
        user.setEmail("janesmith@example.com");
        user.setPassword("Password456");

        when(getUserPortOut.getUserByEmail(anyString())).thenReturn(new UserEntity());

        assertThrows(BusinessException.class, () -> signUpService.execute(user));
    }
}