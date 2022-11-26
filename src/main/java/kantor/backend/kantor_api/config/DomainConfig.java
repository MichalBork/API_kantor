package kantor.backend.kantor_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("kantor.backend.kantor_api.domain")
@EnableJpaRepositories("kantor.backend.kantor_api.repos")
@EnableTransactionManagement
public class DomainConfig {
}
