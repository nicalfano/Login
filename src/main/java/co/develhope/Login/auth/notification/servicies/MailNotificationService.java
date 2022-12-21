package co.develhope.Login.auth.notification.servicies;

import co.develhope.Login.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationService {
    @Autowired
    private JavaMailSender emailSender;
    public void sendActivationMail(User user) {
        SimpleMailMessage sms = new SimpleMailMessage();
        sms.setTo(user.getEmail());
        sms.setSubject("Ti sei iscritto alla piattaforma");
        sms.setText("il codice di attivazione è: "+ user.getActivationCode());
        emailSender.send(sms);
    }

    public void sendPasswordResetMail(User userFromDB) {
        SimpleMailMessage sms = new SimpleMailMessage();
        sms.setTo(userFromDB.getEmail());
        sms.setSubject("Hai richiesto un reset password");
        sms.setText("il codice di reset password è: "+ userFromDB.getPasswordResetCode());
        emailSender.send(sms);
    }
}

