package kantor.backend.kantor_api.repos;

import kantor.backend.kantor_api.domain.SenderLogs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SenderLogsRepository extends JpaRepository<SenderLogs, Long> {
}
