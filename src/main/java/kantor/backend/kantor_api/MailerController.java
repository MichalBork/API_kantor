package kantor.backend.kantor_api;

import kantor.backend.kantor_api.model.ClientsDTO;
import kantor.backend.kantor_api.model.MailRequest;
import kantor.backend.kantor_api.service.ClientsService;
import kantor.backend.kantor_api.service.mailer.MailerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;

@Controller
public class MailerController extends MailerClient {

    private MailerClient mailerClient;

    @Autowired
    public void MailApi(MailerClient mailerClient) {
        this.mailerClient = mailerClient;
    }

    @Autowired
    private ClientsService clientsService;


    @PostMapping("/sendMail")
    @CrossOrigin(origins = "*")
    public String sendMail(@RequestBody MailRequest mailRequest) throws MessagingException {
        ClientsDTO clientsDTO = clientsService.get(mailRequest.getId());
        mailRequest.setMailRecipient(clientsDTO.getEmail());
        mailerClient.sendMail(mailRequest.getMailRecipient(),
                mailRequest.getSubject(),
                mailRequest.getTemplate(), String.valueOf(mailRequest.getId()), true);

        return mailRequest.getTemplate();
    }


}