package cl.corellana.demouser.application.service;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;
import cl.corellana.demouser.adapters.persistence.user.UserMapper;
import cl.corellana.demouser.adapters.web.dto.UserDTO;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.in.SingUpPortIn;
import cl.corellana.demouser.application.port.out.GetUserPortOut;
import cl.corellana.demouser.application.port.out.SaveUserPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SingUpService implements SingUpPortIn {
    private final SaveUserPortOut saveUserPortOut;
    private final GetUserPortOut getUserPortOut;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO execute(UserDTO user) throws BusinessException {
        if (Optional.ofNullable(getUserPortOut.getUserByEmail(user.getEmail())).isPresent())
            throw new BusinessException(409, "User already exists");
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        saveUserPortOut.save(userEntity);
        UserResponseDTO userResponseDTO = UserMapper.INSTANCE.toDTO(userEntity);
        userResponseDTO.setToken(jwtUtils.generateToken(userResponseDTO.getEmail()));
        return userResponseDTO;
    }
}

