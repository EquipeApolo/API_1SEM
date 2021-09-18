package br.gov.sp.fatec.utils.commons;

import br.gov.sp.fatec.utils.exception.Exception.SendEmailFailedException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@EnableAsync
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(String email,String url) {

        try {
            JSONObject base64 = new JSONObject();
            base64.put("dateTime", new Date());
            base64.put("email", email);

            String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());
            String link = url + b64;
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email);
            mailMessage.setSubject("Antenas - Confirmação de conta");
            mailMessage.setText("Para confirmar sua conta, clique no link: " + link);

            mailMessage.setFrom("sendEmailMD@gmail.com");

            javaMailSender.send(mailMessage);
        } catch (Exception ex) {
            throw new SendEmailFailedException();
        }
    }
}
