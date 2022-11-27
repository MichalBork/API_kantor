package kantor.backend.kantor_api.rest;

import java.util.UUID;
import javax.validation.Valid;
import kantor.backend.kantor_api.model.ClientsDTO;
import kantor.backend.kantor_api.service.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.CrossOrigin.DEFAULT_ORIGINS;


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
       if (clientsService.login(clientsDTO.getLogin(), clientsDTO.getPassword()) != null) {
            //return status 200 and userID
            return clientsService.login(clientsDTO.getLogin(), clientsDTO.getPassword()).getId();
       }
        return null;
    }

    //Create jwt token for given login and password json




    //Register new account
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3454")
    public ResponseEntity<Void> register(@RequestBody @Valid final ClientsDTO clientsDTO) {
        clientsService.register(clientsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
