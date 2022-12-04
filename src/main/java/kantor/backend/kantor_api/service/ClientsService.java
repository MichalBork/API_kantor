package kantor.backend.kantor_api.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kantor.backend.kantor_api.domain.Clients;
import kantor.backend.kantor_api.model.ClientsDTO;
import kantor.backend.kantor_api.repos.ClientsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsService(final ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<ClientsDTO> findAll() {
        return clientsRepository.findAll(Sort.by("id"))
                .stream()
                .map(clients -> mapToDTO(clients, new ClientsDTO()))
                .collect(Collectors.toList());
    }

    //activate account
    public void activateAccount(String id) {
        Clients clients = clientsRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        clients.setStatus(1);
        clientsRepository.save(clients);
    }

    public ClientsDTO get(final UUID id) {
        return clientsRepository.findById(id)
                .map(clients -> mapToDTO(clients, new ClientsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final ClientsDTO clientsDTO) {
        final Clients clients = new Clients();
        mapToEntity(clientsDTO, clients);
        return clientsRepository.save(clients).getId();
    }

    public void update(final UUID id, final ClientsDTO clientsDTO) {
        final Clients clients = clientsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(clientsDTO, clients);
        clientsRepository.save(clients);
    }

    public void delete(final UUID id) {
        clientsRepository.deleteById(id);
    }

    private ClientsDTO mapToDTO(final Clients clients, final ClientsDTO clientsDTO) {
        clientsDTO.setId(clients.getId());
        clientsDTO.setName(clients.getName());
        clientsDTO.setSurname(clients.getSurname());
        clientsDTO.setLogin(clients.getLogin());
        clientsDTO.setPassword(clients.getPassword());
        clientsDTO.setAddress(clients.getAddress());
        clientsDTO.setPhone(clients.getPhone());
        clientsDTO.setEmail(clients.getEmail());
        clientsDTO.setStatus(clients.getStatus());
        clientsDTO.setBankAccount(clients.getBankAccount());
        clientsDTO.setBankName(clients.getBankName());
        return clientsDTO;
    }

    private Clients mapToEntity(final ClientsDTO clientsDTO, final Clients clients) {
        clients.setName(clientsDTO.getName());
        clients.setSurname(clientsDTO.getSurname());
        clients.setLogin(clientsDTO.getLogin());
        clients.setPassword(clientsDTO.getPassword());
        clients.setAddress(clientsDTO.getAddress());
        clients.setPhone(clientsDTO.getPhone());
        clients.setEmail(clientsDTO.getEmail());
        clients.setStatus(clientsDTO.getStatus());
        clients.setBankAccount(clientsDTO.getBankAccount());
        clients.setBankName(clientsDTO.getBankName());
        return clients;
    }




    //Login to account
    public ClientsDTO login(final String login, final String password) {
        return clientsRepository.findByLoginAndPassword(login, password)
                .map(clients -> mapToDTO((Clients) clients, new ClientsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Register new account
    public UUID register(final ClientsDTO clientsDTO) {
        final Clients clients = new Clients();
        mapToEntity(clientsDTO, clients);

        return clientsRepository.save(clients).getId();
    }



    //Create jwt token



}
