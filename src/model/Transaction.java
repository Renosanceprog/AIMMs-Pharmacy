package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final String transactionID;
    private final String customerID;
    private final String pharmacistID;
    private final BigDecimal totalAmount;
    private final TransactionStatus status;
    private final LocalDateTime dateTime;

    public Transaction(
            String transactionID,
            String customerID,
            String pharmacistID,
            BigDecimal totalAmount,
            TransactionStatus status,
            LocalDateTime dateTime) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.pharmacistID = pharmacistID;
        this.totalAmount = totalAmount;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPharmacistID() {
        return pharmacistID;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}