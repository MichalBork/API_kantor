package kantor.backend.kantor_api.repos;

import kantor.backend.kantor_api.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthRepository extends JpaRepository<Auth, Long> {
}
