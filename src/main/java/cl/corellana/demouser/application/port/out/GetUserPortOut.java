package cl.corellana.demouser.application.port.out;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;

import java.util.UUID;

public interface GetUserPortOut {

    UserEntity getUserByEmail(String email);
}
