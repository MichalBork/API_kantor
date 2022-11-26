package kantor.backend.kantor_api.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SenderLogs {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String template;

    @Column
    private String senderMail;

    @Column
    private String customerMail;

    @Column
    private String status;

    @Column(columnDefinition = "longtext")
    private String errorLog;

    @Column
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(final String template) {
        this.template = template;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(final String senderMail) {
        this.senderMail = senderMail;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(final String customerMail) {
        this.customerMail = customerMail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(final String errorLog) {
        this.errorLog = errorLog;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

}
