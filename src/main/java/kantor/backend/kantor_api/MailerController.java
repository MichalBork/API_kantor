package kantor.backend.kantor_api;

import kantor.backend.kantor_api.service.mailer.MailerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;

@Controller
public class MailerController extends MailerClient {

    private MailerClient mailerClient;

    @Autowired
    public void MailApi(MailerClient mailerClient) {
        this.mailerClient = mailerClient;
    }

    @GetMapping("/sendMail")
    public String sendMail() throws MessagingException {
        mailerClient.sendMail("",
                "",
                "<b>1000 000 zł</b><br>:P", true);
        return "wysłano";
    }
}