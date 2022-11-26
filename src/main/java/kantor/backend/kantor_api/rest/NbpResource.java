package kantor.backend.kantor_api.rest;

import java.util.List;
import javax.validation.Valid;
import kantor.backend.kantor_api.model.NbpDTO;
import kantor.backend.kantor_api.service.NbpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/nbps", produces = MediaType.APPLICATION_JSON_VALUE)
public class NbpResource {

    private final NbpService nbpService;

    public NbpResource(final NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping
    public ResponseEntity<List<NbpDTO>> getAllNbps() {
        return ResponseEntity.ok(nbpService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NbpDTO> getNbp(@PathVariable final Long id) {
        return ResponseEntity.ok(nbpService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createNbp(@RequestBody @Valid final NbpDTO nbpDTO) {
        return new ResponseEntity<>(nbpService.create(nbpDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNbp(@PathVariable final Long id,
            @RequestBody @Valid final NbpDTO nbpDTO) {
        nbpService.update(id, nbpDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNbp(@PathVariable final Long id) {
        nbpService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //send request to nbp api and save data to database
    @PostMapping("/update")
    public String updateNbp() {
       String resp = String.valueOf(nbpService.updateNbp());
        //nbp update convert to nbpDTO


        return resp;
    }

}
