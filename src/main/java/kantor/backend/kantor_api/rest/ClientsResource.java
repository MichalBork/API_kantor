package kantor.backend.kantor_api.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import kantor.backend.kantor_api.MailerController;
import kantor.backend.kantor_api.model.ClientsDTO;
import kantor.backend.kantor_api.model.MailRequest;
import kantor.backend.kantor_api.service.ClientsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientsResource {

    private final ClientsService clientsService;


    public ClientsResource(final ClientsService clientsService) {
        this.clientsService = clientsService;
    }

   //Login to account with given login and password json

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public UUID login(@RequestBody @Valid final ClientsDTO clientsDTO) {
       if (clientsService.login(clientsDTO.getLogin(), clientsDTO.getPassword()) != null && clientsService.login(clientsDTO.getLogin(), clientsDTO.getPassword()).getStatus() == 1) {
            //return status 200 and userID
            return clientsService.login(clientsDTO.getLogin(), clientsDTO.getPassword()).getId();
       }
        return null;
    }

    //Create jwt token for given login and password json



    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public String activateAccount(@PathVariable final String id) {
        clientsService.activateAccount(id);
        System.out.println("Account activated");
        //redirect to login page
        //return html page
        return "<a href=\"http://localhost:3454/?#/login\">Redirect</a>";
    }


    @GetMapping("/get/{id}")
    @CrossOrigin(origins = "http://localhost:3454")
    public ClientsDTO getUser(@PathVariable final UUID id) {
        System.out.println("Get user"+id);
        return clientsService.get(id);
    }

    //Register new account
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3454")
    public UUID register(@RequestBody @Valid final ClientsDTO clientsDTO, MailerController mailerController, MailRequest mailRequest) {


        return   clientsService.register(clientsDTO);
    }

    //data for profile page
    @GetMapping("/profile/{id}")
    @CrossOrigin(origins = "http://localhost:3454")
    public Map getProfile(@PathVariable final UUID id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", clientsService.get(id));
        map.put("transaction", clientsService.countTransactions(String.valueOf(id)));
        return map;
    }


}
