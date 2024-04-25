package cl.corellana.demouser.application.service;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;
import cl.corellana.demouser.adapters.persistence.user.UserMapper;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.in.LoginPortIn;
import cl.corellana.demouser.application.port.out.GetUserPortOut;
import cl.corellana.demouser.application.port.out.SaveUserPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginPortIn {

    private final JWTUtils jwtUtils;
    private final GetUserPortOut getUserPortOut;
    private final SaveUserPortOut saveUserPortOut;

    @Override
    public UserResponseDTO execute(String token) throws BusinessException {
        if (!jwtUtils.validateToken(token)) {
            throw new BusinessException(401, "Invalid token");
        }

        UserEntity user = getUserPortOut.getUserByEmail(jwtUtils.extractEmail(token));

        Optional.ofNullable(user).orElseThrow(() -> new BusinessException(404, "User not found"));

        user.setLastLogin(LocalDateTime.now());
        saveUserPortOut.save(user);

        UserResponseDTO userResponse = UserMapper.INSTANCE.toDTO(user);
        userResponse.setToken(jwtUtils.generateToken(userResponse.getEmail()));
        return userResponse;
    }
}
