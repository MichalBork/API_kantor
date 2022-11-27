package kantor.backend.kantor_api.rest;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.validation.Valid;
import kantor.backend.kantor_api.model.NbpDTO;
import kantor.backend.kantor_api.service.NbpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/nbp", produces = MediaType.APPLICATION_JSON_VALUE)
public class NbpResource {

    private final NbpService nbpService;

    public NbpResource(final NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<NbpDTO>> getAllNbps() {
        return ResponseEntity.ok(nbpService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NbpDTO> getNbp(@PathVariable final Long id) {
        return ResponseEntity.ok(nbpService.get(id));
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Long> createNbp(@RequestBody @Valid final NbpDTO nbpDTO) {
        return new ResponseEntity<>(nbpService.create(nbpDTO), HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNbp(@PathVariable final Long id) {
        nbpService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //send request to nbp api and save data to database

}
