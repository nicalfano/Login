package co.develhope.Login.user.repositories;

import co.develhope.Login.auth.entities.SignUpActivationDTO;
import co.develhope.Login.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User getByActivationCode(String signUpActivationDTO);


    User findByPasswordResetCode(String resetPasswordCode);
}
