package kantor.backend.kantor_api.service;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.JSONPObject;
import kantor.backend.kantor_api.domain.Clients;
import kantor.backend.kantor_api.model.ClientsDTO;
import kantor.backend.kantor_api.model.TransactionsDTO;
import kantor.backend.kantor_api.repos.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;
    @Autowired
    private final TransactionsService transactionsService;

    public ClientsService(final ClientsRepository clientsRepository, TransactionsService transactionsService) {
        this.clientsRepository = clientsRepository;
        this.transactionsService = transactionsService;
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
        clientsDTO.setRegistrationDate(clients.getRegistrationDate());
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
        clients.setRegistrationDate(clientsDTO.getRegistrationDate());
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


    //Count all transactions for client

    public List<TransactionsDTO> countTransactions(final String id) {
        List<TransactionsDTO> transactionsList = transactionsService.findAll();


        return transactionsList.stream()
                .filter(transaction -> transaction.getClient().equals(id))
                .collect(Collectors.toList());

    }
}
