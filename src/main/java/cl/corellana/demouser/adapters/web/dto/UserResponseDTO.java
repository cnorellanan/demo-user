package cl.corellana.demouser.adapters.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponseDTO extends UserDTO{

    private String id;

    private LocalDateTime created;

    private LocalDateTime lastLogin;

    private String token;

    private boolean isActive;


}
