package kantor.backend.kantor_api.model;

import java.time.LocalDate;
import javax.validation.constraints.Size;


public class NbpDTO {

    private Long id;

    private LocalDate tradingDate;

    @Size(max = 255)
    private String currency;

    private Double bid;

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
