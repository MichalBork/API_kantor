package kantor.backend.kantor_api.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Nbp {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate tradingDate;

    @Column
    private String currency;

    @Column
    private Double bid;

    @Column
    private Double ask;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(final LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(final Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(final Double ask) {
        this.ask = ask;
    }

}
