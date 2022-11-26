package kantor.backend.kantor_api.repos;

import java.util.Optional;
import java.util.UUID;
import kantor.backend.kantor_api.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientsRepository extends JpaRepository<Clients, UUID> {
    Optional<Object> findByLoginAndPassword(String login, String password);
}
