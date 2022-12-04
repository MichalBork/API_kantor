package kantor.backend.kantor_api.service.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailerClient {




        private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;


    @Autowired
        public void MailService(JavaMailSender javaMailSender) {
            this.javaMailSender = javaMailSender;
        }

        public void sendMail(String to,
                             String subject,
                             String text,
                             String userID,
                             boolean isHtmlContent) throws MessagingException {
            MimeMessage message = javaMailSender.createMimeMessage();

            Context context = new Context();

            switch (subject){
                case "Link aktywacyjny":
                    String url = "http://localhost:8080/api/clients/" + userID;
                    context.setVariable("userID", url);
                    break;
                case "Link resetu hasła":
                    //TODO
                    break;
                case "Potwierdzenie":
                    //TODO
                    break;
            }

            String html = templateEngine.process(text, context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(html, isHtmlContent);

            javaMailSender.send(mimeMessage);
        }




    }





