package kantor.backend.kantor_api.service;

import java.util.List;
import java.util.stream.Collectors;
import kantor.backend.kantor_api.domain.Transactions;
import kantor.backend.kantor_api.model.TransactionsDTO;
import kantor.backend.kantor_api.repos.TransactionsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(final TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<TransactionsDTO> findAll() {
        return transactionsRepository.findAll(Sort.by("id"))
                .stream()
                .map(transactions -> mapToDTO(transactions, new TransactionsDTO()))
                .collect(Collectors.toList());
    }

    public TransactionsDTO get(final String id) {
        return transactionsRepository.findById(id)
                .map(transactions -> mapToDTO(transactions, new TransactionsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final TransactionsDTO transactionsDTO) {
        final Transactions transactions = new Transactions();
        mapToEntity(transactionsDTO, transactions);
        transactions.setId(transactionsDTO.getId());
        return transactionsRepository.save(transactions).getId();
    }

    public void update(final String id, final TransactionsDTO transactionsDTO) {
        final Transactions transactions = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(transactionsDTO, transactions);
        transactionsRepository.save(transactions);
    }

    public void delete(final String id) {
        transactionsRepository.deleteById(id);
    }

    private TransactionsDTO mapToDTO(final Transactions transactions,
            final TransactionsDTO transactionsDTO) {
        transactionsDTO.setId(transactions.getId());
        transactionsDTO.setDate(transactions.getDate());
        transactionsDTO.setValue(transactions.getValue());
        transactionsDTO.setType(transactions.getType());
        transactionsDTO.setBidName(transactions.getBidName());
        transactionsDTO.setAskName(transactions.getAskName());
        transactionsDTO.setClient(transactions.getClient());
        transactionsDTO.setNbpValue(transactions.getNbpValue());
        return transactionsDTO;
    }

    private Transactions mapToEntity(final TransactionsDTO transactionsDTO,
            final Transactions transactions) {
        transactions.setDate(transactionsDTO.getDate());
        transactions.setValue(transactionsDTO.getValue());
        transactions.setType(transactionsDTO.getType());
        transactions.setBidName(transactionsDTO.getBidName());
        transactions.setAskName(transactionsDTO.getAskName());
        transactions.setClient(transactionsDTO.getClient());
        transactions.setNbpValue(transactionsDTO.getNbpValue());
        return transactions;
    }

    public boolean idExists(final String id) {
        return transactionsRepository.existsByIdIgnoreCase(id);
    }

}
