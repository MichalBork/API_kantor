package kantor.backend.kantor_api.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.Size;


public class ClientsDTO {

    private UUID id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

    @Size(max = 255)
    private String login;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String email;

    private Integer status;

    private Integer bankAccount;

    @Size(max = 255)
    private String bankName;

    private LocalDate registrationDate;

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(final Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

}
