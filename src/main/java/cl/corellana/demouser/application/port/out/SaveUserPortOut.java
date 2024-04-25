package cl.corellana.demouser.application.port.out;

import cl.corellana.demouser.adapters.persistence.user.UserEntity;

public interface SaveUserPortOut {

    void save(UserEntity user);
}
