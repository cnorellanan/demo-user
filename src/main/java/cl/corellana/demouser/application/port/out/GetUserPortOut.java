package cl.corellana.demouser.application.port.out;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;

public interface GetUserPortOut {

    UserEntity getUserByEmail(String email);
}
