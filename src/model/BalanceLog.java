package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceLog {

    private final String logID;
    private final String customerID;
    private final String actorID;
    private final String actorRole;
    private final BalanceLogAction action;
    private final BigDecimal amount;
    private final BigDecimal balanceBefore;
    private final BigDecimal balanceAfter;
    private final LocalDateTime dateTime;
    private final String details;

    public BalanceLog(
            String logID,
            String customerID,
            String actorID,
            String actorRole,
            BalanceLogAction action,
            BigDecimal amount,
            BigDecimal balanceBefore,
            BigDecimal balanceAfter,
            LocalDateTime dateTime,
            String details) {
        this.logID = logID;
        this.customerID = customerID;
        this.actorID = actorID;
        this.actorRole = actorRole;
        this.action = action;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getLogID() {
        return logID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getActorID() {
        return actorID;
    }

    public String getActorRole() {
        return actorRole;
    }

    public BalanceLogAction getAction() {
        return action;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }
}