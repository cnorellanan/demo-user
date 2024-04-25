package cl.corellana.demouser.adapters.persistence.user;

import cl.corellana.demouser.application.port.out.GetUserPortOut;
import cl.corellana.demouser.application.port.out.SaveUserPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPortOut, GetUserPortOut {

    private final UserRepository userRepository;

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
