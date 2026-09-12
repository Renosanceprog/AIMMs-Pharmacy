package model.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer extends User {

    private final BigDecimal balance;
    private final CustomerStatus status;
    private final LocalDate dateRegistered;

    public Customer(
            String userID,
            String username,
            String password,
            String fullName,
            BigDecimal balance,
            CustomerStatus status,
            LocalDate dateRegistered) {
        super(userID, username, password, fullName);
        this.balance = balance;
        this.status = status;
        this.dateRegistered = dateRegistered;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    @Override
    public UserRole getRole() {
        return UserRole.CUSTOMER;
    }
}