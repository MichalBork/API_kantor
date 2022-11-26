package kantor.backend.kantor_api.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Transactions {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private LocalDate date;

    @Column
    private Double value;

    @Column
    private Integer type;

    @Column
    private String bidName;

    @Column
    private String askName;

    @Column
    private String client;

    @Column
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
