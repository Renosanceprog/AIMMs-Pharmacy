package model;

import java.time.LocalDateTime;

public class TransactionLog {

    private final String logID;
    private final String transactionID;
    private final String customerID;
    private final String actorID;
    private final String actorRole;
    private final TransactionLogAction action;
    private final LocalDateTime dateTime;
    private final String details;

    public TransactionLog(
            String logID,
            String transactionID,
            String customerID,
            String actorID,
            String actorRole,
            TransactionLogAction action,
            LocalDateTime dateTime,
            String details) {
        this.logID = logID;
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.actorID = actorID;
        this.actorRole = actorRole;
        this.action = action;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getLogID() {
        return logID;
    }

    public String getTransactionID() {
        return transactionID;
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

    public TransactionLogAction getAction() {
        return action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }
}