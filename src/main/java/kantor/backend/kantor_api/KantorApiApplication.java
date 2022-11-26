package kantor.backend.kantor_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3454")
public class KantorApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(KantorApiApplication.class, args);
    }

}
