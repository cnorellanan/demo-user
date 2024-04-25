package cl.corellana.demouser.application.port.in;

import cl.corellana.demouser.adapters.web.dto.UserDTO;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;

public interface SingUpPortIn {

    UserResponseDTO execute(UserDTO user) throws BusinessException;
}
