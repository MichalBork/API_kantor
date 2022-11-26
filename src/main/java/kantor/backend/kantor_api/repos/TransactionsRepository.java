package kantor.backend.kantor_api.repos;

import kantor.backend.kantor_api.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionsRepository extends JpaRepository<Transactions, String> {

    boolean existsByIdIgnoreCase(String id);

}
