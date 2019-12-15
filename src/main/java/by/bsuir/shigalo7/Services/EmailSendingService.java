package by.bsuir.shigalo7.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService /*implements EmailService*/ {

    @Autowired
    public JavaMailSender emailSender;

    public void sendMessage(/*String to, String subject, String text*/) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sashshig99@gmail.com");
        message.setSubject("Test");
        message.setText("test text");
        emailSender.send(message);
    }

}
