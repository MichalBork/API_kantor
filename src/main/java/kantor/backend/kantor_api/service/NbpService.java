package kantor.backend.kantor_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import kantor.backend.kantor_api.domain.Nbp;
import kantor.backend.kantor_api.model.NbpDTO;
import kantor.backend.kantor_api.repos.NbpRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class NbpService {

    private final NbpRepository nbpRepository;

    public NbpService(final NbpRepository nbpRepository) {
        this.nbpRepository = nbpRepository;
    }

    public List<NbpDTO> findAll() {
        return nbpRepository.findAll(Sort.by("id"))
                .stream()
                .map(nbp -> mapToDTO(nbp, new NbpDTO()))
                .collect(Collectors.toList());
    }

    public List<NbpDTO> findAllByNewest() {
        return nbpRepository.findAll(Sort.by("tradingDate").descending())
                .stream()
                .map(nbp -> mapToDTO(nbp, new NbpDTO()))
                .collect(Collectors.toList());
    }

    //when date is today add element to list
    public List<NbpDTO> findAllByDate() {
        LocalDate date = checkWhatDateIsNewest();
        List<NbpDTO> nbpDTOList = findAll();
        List<NbpDTO> nbpDTOListToReturn = new ArrayList();
        for (NbpDTO nbpDTO : nbpDTOList) {
            if (nbpDTO.getTradingDate().equals(date)) {
                nbpDTOListToReturn.add(nbpDTO);
            }
        }
        return nbpDTOListToReturn;
    }

    public LocalDate checkWhatDateIsNewest() {
        List<NbpDTO> sortedList = findAllByNewest();
        LocalDate newestDate = sortedList.get(0).getTradingDate();
        LocalDate today = LocalDate.now();
        if (newestDate.equals(today)) {
        } else {
            do {
                today = today.minusDays(1);
                if (today.equals(newestDate)) {
                    break;
                }
            } while (true);
        }
        return today;
    }

    public NbpDTO get(final Long id) {
        return nbpRepository.findById(id)
                .map(nbp -> mapToDTO(nbp, new NbpDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final NbpDTO nbpDTO) {
        final Nbp nbp = new Nbp();
        mapToEntity(nbpDTO, nbp);
        return nbpRepository.save(nbp).getId();
    }

    public void update(final Long id, final NbpDTO nbpDTO) {
        final Nbp nbp = nbpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(nbpDTO, nbp);
        nbpRepository.save(nbp);
    }

    public void delete(final Long id) {
        nbpRepository.deleteById(id);
    }

    private NbpDTO mapToDTO(final Nbp nbp, final NbpDTO nbpDTO) {
        nbpDTO.setId(nbp.getId());
        nbpDTO.setTradingDate(nbp.getTradingDate());
        nbpDTO.setCurrency(nbp.getCurrency());
        nbpDTO.setBid(nbp.getBid());
        nbpDTO.setAsk(nbp.getAsk());
        return nbpDTO;
    }

    private Nbp mapToEntity(final NbpDTO nbpDTO, final Nbp nbp) {
        nbp.setTradingDate(nbpDTO.getTradingDate());
        nbp.setCurrency(nbpDTO.getCurrency());
        nbp.setBid(nbpDTO.getBid());
        nbp.setAsk(nbpDTO.getAsk());
        return nbp;
    }

//    public HttpEntity<String> updateNbp() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("format", "json");
//
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-04/?format=json");
//
//
//
//
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        HttpEntity<String> response = restTemplate.exchange(
//                builder.toUriString(),
//                HttpMethod.GET,
//                entity,
//                String.class);
//
//        System.out.print(response.getBody());
//        exit(0);
//
//        return  response;
//
//    }


}
