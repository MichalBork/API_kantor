package kantor.backend.kantor_api.repos;

import kantor.backend.kantor_api.domain.Nbp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NbpRepository extends JpaRepository<Nbp, Long> {
}
