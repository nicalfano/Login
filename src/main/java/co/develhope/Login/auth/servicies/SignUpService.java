package co.develhope.Login.auth.servicies;

import co.develhope.Login.auth.entities.SignUpActivationDTO;
import co.develhope.Login.auth.entities.SignUpDTO;
import co.develhope.Login.auth.notification.servicies.MailNotificationService;
import co.develhope.Login.user.entities.User;
import co.develhope.Login.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SignUpService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MailNotificationService mailNotificationService;
    public User signUp (SignUpDTO signUpDTO) throws Exception {
    User userFromDB = userRepository.findByEmail(signUpDTO.getEmail());
    if(userFromDB != null) throw new Exception("User already exists");
    User user = new User();
    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
    user.setName(signUpDTO.getName());
    user.setEmail(signUpDTO.getEmail());
    user.setSurname(signUpDTO.getSurname());
    user.setActvive(false);
    user.setActivationCode(UUID.randomUUID().toString());
    mailNotificationService.sendActivationMail(user);
    return userRepository.save(user);
    }

    public User activate(SignUpActivationDTO signUpActivationDTO) throws Exception {
        User user = userRepository.getByActivationCode(signUpActivationDTO.getActivationCode());
        if(user == null) throw new Exception("User not found!");
        user.setActvive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }
}
