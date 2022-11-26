package kantor.backend.kantor_api.repos;

import kantor.backend.kantor_api.domain.Sender;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SenderRepository extends JpaRepository<Sender, Long> {
}
