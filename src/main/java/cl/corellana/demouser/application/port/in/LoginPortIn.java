package cl.corellana.demouser.application.port.in;

import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;

public interface LoginPortIn {
    UserResponseDTO execute(String token) throws BusinessException;
}
