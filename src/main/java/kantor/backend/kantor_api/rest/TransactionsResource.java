package kantor.backend.kantor_api.rest;

import java.util.List;
import javax.validation.Valid;
import kantor.backend.kantor_api.model.TransactionsDTO;
import kantor.backend.kantor_api.service.TransactionsService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/transactionss", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsResource {

    private final TransactionsService transactionsService;

    public TransactionsResource(final TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionsDTO>> getAllTransactionss() {
        return ResponseEntity.ok(transactionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsDTO> getTransactions(@PathVariable final String id) {
        return ResponseEntity.ok(transactionsService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createTransactions(
            @RequestBody @Valid final TransactionsDTO transactionsDTO,
            final BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (!bindingResult.hasFieldErrors("id") && transactionsDTO.getId() == null) {
            bindingResult.rejectValue("id", "NotNull");
        }
        if (!bindingResult.hasFieldErrors("id") && transactionsService.idExists(transactionsDTO.getId())) {
            bindingResult.rejectValue("id", "Exists.transactions.id");
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(
                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
        }
        transactionsService.create(transactionsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransactions(@PathVariable final String id,
            @RequestBody @Valid final TransactionsDTO transactionsDTO) {
        transactionsService.update(id, transactionsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactions(@PathVariable final String id) {
        transactionsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
