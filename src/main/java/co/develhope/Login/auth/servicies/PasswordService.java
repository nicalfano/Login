package co.develhope.Login.auth.servicies;

import co.develhope.Login.auth.entities.RequestPasswordDTO;
import co.develhope.Login.auth.entities.RestorePasswordDTO;
import co.develhope.Login.auth.notification.servicies.MailNotificationService;
import co.develhope.Login.user.entities.User;
import co.develhope.Login.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User request(RequestPasswordDTO requestPasswordDTO) throws Exception {
        User userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if(userFromDB == null) throw new Exception("User is null!");
        userFromDB.setPasswordResetCode(UUID.randomUUID().toString());
        mailNotificationService.sendPasswordResetMail(userFromDB);
        return userRepository.save(userFromDB);

    }
    public User restore(RestorePasswordDTO restorePasswordDTO) throws Exception{
        User userFromDB = userRepository.findByPasswordResetCode(restorePasswordDTO.getResetPasswordCode());
        if(userFromDB == null) throw new Exception("User is null");
        userFromDB.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
        userFromDB.setActvive(true);
        userFromDB.setPasswordResetCode(null);

        return userRepository.save(userFromDB);

    }
}
