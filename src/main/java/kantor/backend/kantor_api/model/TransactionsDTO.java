package kantor.backend.kantor_api.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.Size;


public class TransactionsDTO {

    @Size(max = 255)
    private String id = UUID.randomUUID().toString();

    private LocalDate date;

    private Double value;

    private Integer type; // 1 - buy, 2 - sell

    @Size(max = 255)
    private String bidName;// cena kupna

    @Size(max = 255)
    private String askName; //cena sprzedaży

    @Size(max = 255)
    private String client;

    @Size(max = 255)
    private String nbpValue;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(final String bidName) {
        this.bidName = bidName;
    }

    public String getAskName() {
        return askName;
    }

    public void setAskName(final String askName) {
        this.askName = askName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(final String client) {
        this.client = client;
    }

    public String getNbpValue() {
        return nbpValue;
    }

    public void setNbpValue(final String nbpValue) {
        this.nbpValue = nbpValue;
    }

}
